package com.subjunctify

import org.junit.Assert.assertEquals
import org.junit.Assert.assertNull
import org.junit.Assert.assertTrue
import org.junit.Test
import java.time.Duration
import java.time.Instant
import java.time.ZoneId
import java.time.ZonedDateTime

class ReviewTest {
    private val zone = ZoneId.of("Europe/Madrid")
    private val evening = ZonedDateTime.of(2026, 11, 5, 21, 0, 0, 0, zone).toInstant()
    private val exerciseId = exercisesById.keys.first()
    private val newCard = ReviewCard(exerciseId, Mode.TRANSLATE)

    private fun startOfDay(year: Int, month: Int, day: Int) =
        ZonedDateTime.of(year, month, day, 0, 0, 0, 0, zone).toInstant().toEpochMilli()

    /** Answers correctly whenever the card falls due, returning the intervals along the way. */
    private fun passRepeatedly(card: ReviewCard, times: Int): List<Int> {
        var current = card
        return List(times) {
            current = current.afterAnswer(true, Instant.ofEpochMilli(current.dueAt), zone)
            current.intervalDays
        }
    }

    @Test
    fun aLessonWithOneModeFinishedIsNotStarted() {
        val rounds = listOf(LessonRound("lesson", Mode.TRANSLATE, 1_000))
        assertEquals(emptyMap<String, Instant>(), rounds.lessonStartTimes())
    }

    @Test
    fun aLessonStartsWhenItsLastModeIsFinished() {
        val rounds = listOf(
            LessonRound("lesson", Mode.LISTEN, 2_000),
            LessonRound("lesson", Mode.TRANSLATE, 1_000),
            LessonRound("other", Mode.LISTEN, 3_000),
        )
        assertEquals(mapOf("lesson" to Instant.ofEpochMilli(2_000)), rounds.lessonStartTimes())
    }

    @Test
    fun speakingIsNotNeededToStartALessonAndDoesNotMoveItsDate() {
        val rounds = listOf(
            LessonRound("lesson", Mode.SPEAK, 3_000),
            LessonRound("lesson", Mode.LISTEN, 2_000),
            LessonRound("lesson", Mode.TRANSLATE, 1_000),
            LessonRound("other", Mode.SPEAK, 1_000),
            LessonRound("other", Mode.TRANSLATE, 1_000),
        )
        assertEquals(mapOf("lesson" to Instant.ofEpochMilli(2_000)), rounds.lessonStartTimes())
    }

    @Test
    fun onlyStartedLessonsOfTheCourseAreCompleted() {
        val catalan = courses.first { it.language == Language.CATALAN }
        val second = catalan.lessons[1]
        val startTimes = mapOf(second.id to evening, "another-course-lesson" to evening)
        assertEquals(listOf(second), catalan.completedLessons(startTimes))
    }

    @Test
    fun lessonIdsIdentifyOneLessonEach() {
        val lessons = courses.flatMap { it.lessons }
        for ((id, sameId) in lessons.groupBy { it.id }) {
            assertEquals(id, 1, sameId.map { it.title }.toSet().size)
        }
        // The same subject in two courses shares its ID, so its progress carries across.
        for ((title, sameTitle) in lessons.groupBy { it.title }) {
            assertEquals(title, 1, sameTitle.map { it.id }.toSet().size)
        }
    }

    @Test
    fun passingLengthensTheInterval() {
        assertEquals(listOf(1, 3, 8, 20, 50, 125, 313), passRepeatedly(newCard, 7))
    }

    @Test
    fun intervalsAreCappedAtAYear() {
        assertEquals(365, passRepeatedly(newCard, 12).last())
    }

    @Test
    fun cardFallsDueAtTheStartOfTheDayNotTheTimeOfPractice() {
        assertEquals(startOfDay(2026, 11, 6), newCard.afterAnswer(true, evening, zone).dueAt)
    }

    @Test
    fun dueDatesFollowLocalMidnightAcrossClockChanges() {
        val beforeClocksChange = ZonedDateTime.of(2026, 10, 24, 12, 0, 0, 0, zone).toInstant()
        assertEquals(startOfDay(2026, 10, 25), newCard.afterAnswer(true, beforeClocksChange, zone).dueAt)
        val learned = newCard.copy(repetitions = 1)
        assertEquals(startOfDay(2026, 10, 27), learned.afterAnswer(true, beforeClocksChange, zone).dueAt)
    }

    @Test
    fun failingBringsTheCardBackSoonAndRestartsIt() {
        val learned = ReviewCard(exerciseId,Mode.TRANSLATE, repetitions = 4, intervalDays = 20)
        val failed = learned.afterAnswer(false, evening, zone)
        assertEquals(0, failed.repetitions)
        assertEquals(evening.plus(Duration.ofMinutes(10)).toEpochMilli(), failed.dueAt)
        assertEquals(2.3, failed.ease, 0.001)

        val retried = failed.afterAnswer(true, evening.plus(Duration.ofMinutes(10)), zone)
        assertEquals(1, retried.intervalDays)
    }

    @Test
    fun onlyLapsesLowerEaseAndNeverBelowTheMinimum() {
        assertEquals(2.5, newCard.afterAnswer(false, evening, zone).ease, 0.001)
        val hard = ReviewCard(exerciseId,Mode.TRANSLATE, repetitions = 2, ease = 1.4)
        assertEquals(1.3, hard.afterAnswer(false, evening, zone).ease, 0.001)
    }

    @Test
    fun passingEarlyChangesNothingButFailingEarlyCounts() {
        val scheduled = newCard.afterAnswer(true, evening, zone)
        assertEquals(scheduled, scheduled.afterAnswer(true, evening.plusSeconds(60), zone))
        assertEquals(0, scheduled.afterAnswer(false, evening.plusSeconds(60), zone).repetitions)
    }

    @Test
    fun dueCardsAreSeparatedByMode() {
        val cards = listOf(
            ReviewCard(exerciseId,Mode.TRANSLATE, dueAt = evening.toEpochMilli()),
            ReviewCard(exerciseId,Mode.LISTEN, dueAt = evening.plusSeconds(1).toEpochMilli()),
        )
        assertEquals(1, cards.due(Mode.TRANSLATE, evening).size)
        assertEquals(0, cards.due(Mode.LISTEN, evening).size)
        assertEquals(1, cards.due(Mode.LISTEN, evening.plusSeconds(1)).size)
    }

    @Test
    fun sessionTakesTheLongestWaitingCardsUpToTheLimit() {
        val cards = exercisesById.keys.mapIndexed { index, it ->
            ReviewCard(it, Mode.TRANSLATE, dueAt = index.toLong())
        }
        assertEquals(exercisesById.keys.take(20).toSet(), cards.nextSession(Mode.TRANSLATE, evening).toSet())
    }

    @Test
    fun cardsNoLongerInTheCourseAreIgnored() {
        val removed = listOf(ReviewCard("es-b2-removed", Mode.TRANSLATE))
        for (course in courses) assertTrue(removed.inCourse(course).isEmpty())
        assertNull(emptyList<ReviewCard>().nextReview(evening, zone))
    }

    @Test
    fun nextReviewIsDescribedInCalendarDays() {
        fun describe(dueAt: Long) =
            listOf(ReviewCard(exerciseId,Mode.TRANSLATE, dueAt = dueAt)).nextReview(evening, zone)
        assertEquals("later today", describe(evening.plus(Duration.ofMinutes(10)).toEpochMilli()))
        assertEquals("tomorrow", describe(startOfDay(2026, 11, 6)))
        assertEquals("in 3 days", describe(startOfDay(2026, 11, 8)))
    }

    @Test
    fun everyExerciseHasItsOwnWellFormedId() {
        val exercises = courses.flatMap { it.lessons }.flatMap { it.exercises }.distinct()
        val duplicates = exercises.groupBy { it.id }.filterValues { it.size > 1 }.keys
        assertEquals(emptySet<String>(), duplicates)
        val format = Regex("""(es-subj|es-b2|ca-a1)-\d{3,}""")
        for (exercise in exercises) assertTrue(exercise.id, format.matches(exercise.id))
    }

    @Test
    fun reviewsStayInsideTheirCourse() {
        val catalan = courses.first { it.language == Language.CATALAN }
        val spanish = courses.first { it.title == "Spanish grammar" }
        val card = ReviewCard(catalan.lessons.first().exercises.first().id, Mode.TRANSLATE)
        assertEquals(listOf(card), listOf(card).inCourse(catalan))
        assertTrue(listOf(card).inCourse(spanish).isEmpty())
    }

    @Test
    fun subjectsSharedBetweenSpanishCoursesShareTheirSchedule() {
        val subjunctive = courses.first { it.title == "The Spanish subjunctive" }
        val spanish = courses.first { it.title == "Spanish grammar" }
        val shared = spanish.lessons.first { it.title == "Imperfecto de subjuntivo" }
        val card = ReviewCard(shared.exercises.first().id, Mode.TRANSLATE)
        assertEquals(listOf(card), listOf(card).inCourse(subjunctive))
        assertEquals(listOf(card), listOf(card).inCourse(spanish))
    }

    @Test
    fun spanishCourseHasTheRequestedSubjectsAndCategories() {
        val spanish = courses.first { it.title == "Spanish grammar" }
        val functional = "Verbs: Functional"
        val past = "Verbs: Past"
        val discourse = "Discourse, sentence and phrase types"
        val subjunctive = "Verbs: Subjuntivo"
        val imperative = "Verbs: Imperative"
        val expected = listOf(
            functional to "Explaining what you can do",
            past to "Reviewing past tenses",
            past to "Pretérito imperfecto",
            discourse to "Making recommendations",
            subjunctive to "Expanding on the subjunctive",
            subjunctive to "Evaluations with the subjuntivo",
            subjunctive to "Imperfecto de subjuntivo",
            functional to "The infinitivo and the gerundio",
            discourse to "Expressing unlikely conditions",
            discourse to "Expressing certainty and uncertainty",
            imperative to "Imperativo afirmativo + pronouns",
            imperative to "Imperativo negativo + pronouns",
            discourse to "Expressing conditions",
            discourse to "Expressing purpose",
            discourse to "Expressing unlikely wishes",
            "Adverbs" to "“Antes de” and “después de”",
            functional to "Proposals and suggestions",
            discourse to "Commands and reported speech",
            "Verbs: Future" to "Futuro perfecto",
            "Verbs: Ser y Estar" to "Adjectives + Ser y Estar",
            discourse to "Consecutive sentences",
            "Referencing" to "Relative clauses",
            discourse to "Concessive phrases",
            "Connectors" to "Connectors: cause and effect",
            discourse to "Forming hypotheses and assumptions",
            "Verbs: Passive" to "The passive voice",
            functional to "Regretting decisions from the past",
            discourse to "Hypothetical situations in the past",
            subjunctive to "Pluscuamperfecto de subjuntivo",
            subjunctive to "Indicativo y subjuntivo",
        )
        assertEquals(expected, spanish.lessons.map { it.category to it.title })
        for (lesson in spanish.lessons) assertEquals(lesson.title, "B2", lesson.level)
    }

    @Test
    fun catalanLessonsAreA1AndCategorised() {
        val catalan = courses.first { it.language == Language.CATALAN }
        for (lesson in catalan.lessons) {
            assertEquals(lesson.title, "A1", lesson.level)
            assertTrue(lesson.title, !lesson.category.isNullOrBlank())
        }
    }
}
