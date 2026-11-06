package com.subjunctify

import android.content.Context
import androidx.room.Dao
import androidx.room.Database
import androidx.room.Query
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.Transaction
import androidx.room.Upsert
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

    @Query("SELECT * FROM review_card WHERE sentence = :sentence AND mode = :mode")
    suspend fun find(sentence: String, mode: Mode): ReviewCard?

    @Upsert
    suspend fun save(card: ReviewCard)

    @Transaction
    suspend fun recordAnswer(sentence: String, mode: Mode, passed: Boolean, now: Instant, zone: ZoneId) {
        val card = find(sentence, mode) ?: ReviewCard(sentence, mode)
        save(card.afterAnswer(passed, now, zone))
    }
}

@Database(entities = [ReviewCard::class], version = 1, exportSchema = false)
abstract class ReviewDatabase : RoomDatabase() {
    abstract fun cards(): ReviewCardDao
}

/** One per process, because an open database must outlive activities recreated by rotation. */
class ReviewStore private constructor(context: Context) {
    private val dao = Room
        .databaseBuilder(context.applicationContext, ReviewDatabase::class.java, "reviews.db")
        .build()
        .cards()

    // Not tied to a screen, so leaving straight after answering can't cancel the save.
    private val writes = CoroutineScope(SupervisorJob() + Dispatchers.IO)

    val cards: Flow<List<ReviewCard>> = dao.observeAll()

    fun recordAnswer(sentence: String, mode: Mode, passed: Boolean) {
        val now = Instant.now()
        val zone = ZoneId.systemDefault()
        writes.launch { dao.recordAnswer(sentence, mode, passed, now, zone) }
    }

    companion object {
        private var instance: ReviewStore? = null

        /** Call from the main thread. */
        fun get(context: Context): ReviewStore =
            instance ?: ReviewStore(context).also { instance = it }
    }
}
