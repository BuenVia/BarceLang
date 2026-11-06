package com.subjunctify

import androidx.room.Entity
import java.time.Duration
import java.time.Instant
import java.time.LocalDate
import java.time.ZoneId
import java.time.temporal.ChronoUnit
import kotlin.math.ceil
import kotlin.math.max

private const val STARTING_EASE = 2.5
private const val MINIMUM_EASE = 1.3
private const val EASE_PENALTY = 0.2
private const val MAXIMUM_INTERVAL_DAYS = 365
private const val MAXIMUM_SESSION_SIZE = 20

/** A missed sentence comes back soon, then starts climbing the intervals again. */
private val RETRY_DELAY: Duration = Duration.ofMinutes(10)

/**
 * Every sentence in the app by its model answer, which is what [ReviewCard.sentence] stores. A lesson
 * can belong to more than one course, and its sentences then share one schedule across them.
 */
val sentences: Map<String, Exercise> =
    courses.flatMap { it.lessons }.flatMap { it.exercises }.associateBy { it.answer }

/**
 * The schedule for one sentence in one mode: recognising a sentence by ear doesn't mean you can
 * produce it, so the two are remembered separately.
 *
 * Cards are keyed on the model answer, so rewording one in a lesson restarts its schedule. Cards
 * for sentences no longer in a course stay in the database but are ignored.
 */
@Entity(tableName = "review_card", primaryKeys = ["sentence", "mode"])
data class ReviewCard(
    val sentence: String,
    val mode: Mode,
    val repetitions: Int = 0,
    val intervalDays: Int = 0,
    val ease: Double = STARTING_EASE,
    val dueAt: Long = 0,
) {
    /**
     * The schedule after an answer, using a simplified SM-2. Answers are only right or wrong, so
     * ease falls on a lapse and otherwise holds steady.
     *
     * Passing before the due date changes nothing, because cramming shouldn't postpone a review.
     * Failing always counts, as it shows the sentence has been forgotten.
     */
    fun afterAnswer(passed: Boolean, now: Instant, zone: ZoneId): ReviewCard {
        if (!passed) {
            return copy(
                repetitions = 0,
                intervalDays = 0,
                // Only a lapse lowers ease: repeatedly missing a new sentence isn't one.
                ease = if (repetitions > 0) max(MINIMUM_EASE, ease - EASE_PENALTY) else ease,
                dueAt = now.plus(RETRY_DELAY).toEpochMilli(),
            )
        }
        if (dueAt > now.toEpochMilli()) return this

        val interval = when (repetitions) {
            0 -> 1
            1 -> 3
            else -> max(intervalDays + 1, ceil(intervalDays * ease).toInt())
        }.coerceAtMost(MAXIMUM_INTERVAL_DAYS)
        return copy(
            repetitions = repetitions + 1,
            intervalDays = interval,
            // Due from the start of the day, so reviews don't depend on the time of day you practised.
            dueAt = now.atZone(zone).toLocalDate().plusDays(interval.toLong())
                .atStartOfDay(zone).toInstant().toEpochMilli(),
        )
    }
}

fun List<ReviewCard>.inCourse(course: Course): List<ReviewCard> =
    filter { it.sentence in course.sentences }

fun List<ReviewCard>.due(mode: Mode, now: Instant): List<ReviewCard> =
    filter { it.mode == mode && it.dueAt <= now.toEpochMilli() }

/** The longest-waiting due sentences, shuffled so that neighbours in a lesson don't cue each other. */
fun List<ReviewCard>.nextSession(mode: Mode, now: Instant): List<String> =
    due(mode, now).sortedBy { it.dueAt }.take(MAXIMUM_SESSION_SIZE).map { it.sentence }.shuffled()

/** When the next card falls due, as in "tomorrow". Null when there are no cards. */
fun List<ReviewCard>.nextReview(now: Instant, zone: ZoneId): String? {
    val dueAt = minOfOrNull { it.dueAt } ?: return null
    val today: LocalDate = now.atZone(zone).toLocalDate()
    val dueDay: LocalDate = Instant.ofEpochMilli(dueAt).atZone(zone).toLocalDate()
    return when (val days = ChronoUnit.DAYS.between(today, dueDay)) {
        in Long.MIN_VALUE..0 -> "later today"
        1L -> "tomorrow"
        else -> "in $days days"
    }
}
