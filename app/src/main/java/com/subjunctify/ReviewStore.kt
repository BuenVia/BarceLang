package com.subjunctify

import android.content.Context
import androidx.room.Dao
import androidx.room.Database
import androidx.room.Query
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.Transaction
import androidx.room.Upsert
import androidx.room.migration.Migration
import androidx.sqlite.db.SupportSQLiteDatabase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch
import java.time.Instant
import java.time.ZoneId

@Dao
interface ReviewCardDao {
    @Query("SELECT * FROM review_card")
    fun observeAll(): Flow<List<ReviewCard>>

    @Query("SELECT * FROM review_card WHERE exerciseId = :exerciseId AND mode = :mode")
    suspend fun find(exerciseId: String, mode: Mode): ReviewCard?

    @Upsert
    suspend fun save(card: ReviewCard)

    @Transaction
    suspend fun recordAnswer(exerciseId: String, mode: Mode, passed: Boolean, now: Instant, zone: ZoneId) {
        val card = find(exerciseId, mode) ?: ReviewCard(exerciseId, mode)
        save(card.afterAnswer(passed, now, zone))
    }
}

@Database(entities = [ReviewCard::class], version = 2, exportSchema = false)
abstract class ReviewDatabase : RoomDatabase() {
    abstract fun cards(): ReviewCardDao
}

/**
 * Moves cards from being keyed on a sentence's model answer to its permanent ID. The table is rebuilt
 * because SQLite can't rename a column before API 30. A card whose answer matches no current sentence
 * keeps its old key, and stays ignored as it was before.
 */
private val KEY_CARDS_BY_EXERCISE_ID = object : Migration(1, 2) {
    override fun migrate(db: SupportSQLiteDatabase) {
        db.execSQL(
            """
            CREATE TABLE review_card_new (
                exerciseId TEXT NOT NULL,
                mode TEXT NOT NULL,
                repetitions INTEGER NOT NULL,
                intervalDays INTEGER NOT NULL,
                ease REAL NOT NULL,
                dueAt INTEGER NOT NULL,
                PRIMARY KEY(exerciseId, mode)
            )
            """,
        )
        db.execSQL(
            """
            INSERT INTO review_card_new (exerciseId, mode, repetitions, intervalDays, ease, dueAt)
            SELECT sentence, mode, repetitions, intervalDays, ease, dueAt FROM review_card
            """,
        )
        for (exercise in exercisesById.values) {
            db.execSQL(
                "UPDATE review_card_new SET exerciseId = ? WHERE exerciseId = ?",
                arrayOf(exercise.id, exercise.answer),
            )
        }
        db.execSQL("DROP TABLE review_card")
        db.execSQL("ALTER TABLE review_card_new RENAME TO review_card")
    }
}

/** One per process, because an open database must outlive activities recreated by rotation. */
class ReviewStore private constructor(context: Context) {
    private val dao = Room
        .databaseBuilder(context.applicationContext, ReviewDatabase::class.java, "reviews.db")
        .addMigrations(KEY_CARDS_BY_EXERCISE_ID)
        .build()
        .cards()

    // Not tied to a screen, so leaving straight after answering can't cancel the save.
    private val writes = CoroutineScope(SupervisorJob() + Dispatchers.IO)

    val cards: Flow<List<ReviewCard>> = dao.observeAll()

    fun recordAnswer(exerciseId: String, mode: Mode, passed: Boolean) {
        val now = Instant.now()
        val zone = ZoneId.systemDefault()
        writes.launch { dao.recordAnswer(exerciseId, mode, passed, now, zone) }
    }

    companion object {
        private var instance: ReviewStore? = null

        /** Call from the main thread. */
        fun get(context: Context): ReviewStore =
            instance ?: ReviewStore(context).also { instance = it }
    }
}
