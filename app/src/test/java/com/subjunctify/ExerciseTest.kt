package com.subjunctify

import org.junit.Assert.assertEquals
import org.junit.Assert.assertFalse
import org.junit.Test

class ExerciseTest {
    private val exercise = Exercise(
        "test",
        "I want you (tú) to come.",
        "{|yo }quiero que {|tú }{vengas|vinieras}.",
        "Que {|tú }vengas, {|yo }quiero.",
    )

    @Test
    fun modelAnswerUsesFirstOptionsAndIsCapitalised() {
        assertEquals("Quiero que vengas.", exercise.answer)
        assertEquals("¿Hay alguien?", Exercise("test", "","¿{hay|existe} alguien?").answer)
    }

    @Test
    fun translationAcceptsEveryListedWording() {
        for (answer in listOf(
            "Quiero que vengas.",
            "Yo quiero que tú vengas",
            "quiero que vinieras",
            "Que vengas, quiero",
        )) {
            assertEquals(answer, Verdict.CORRECT, exercise.check(answer, Mode.TRANSLATE))
        }
    }

    @Test
    fun caseSpacingAndPunctuationAreIgnored() {
        assertEquals(Verdict.CORRECT, exercise.check("  QUIERO   que, vengas!! ", Mode.TRANSLATE))
    }

    @Test
    fun decomposedUnicodeMatchesComposed() {
        val tu = Exercise("test", "","tú")
        assertEquals(Verdict.CORRECT, tu.check("tú", Mode.TRANSLATE))
    }

    @Test
    fun wrongMoodIsIncorrect() {
        assertEquals(Verdict.WRONG, exercise.check("Quiero que vienes.", Mode.TRANSLATE))
    }

    @Test
    fun missingAccentIsIncorrectButFlagged() {
        val hablo = Exercise("test", "","Ojalá hable.")
        assertEquals(Verdict.ACCENTS_ONLY, hablo.check("Ojala hable", Mode.TRANSLATE))
        assertEquals(Verdict.ACCENTS_ONLY, hablo.check("Ojalá hablé", Mode.TRANSLATE))
    }

    @Test
    fun transcriptionOnlyAcceptsWhatWasSpoken() {
        assertEquals(Verdict.CORRECT, exercise.check("quiero que vengas", Mode.LISTEN))
        assertEquals(Verdict.WRONG, exercise.check("yo quiero que vengas", Mode.LISTEN))
        assertEquals(Verdict.WRONG, exercise.check("quiero que vinieras", Mode.LISTEN))
    }

    @Test
    fun geminateLCanBeTypedWithAFullStopOrHyphen() {
        val exercise = Exercise("test", "","La Marta és intel·ligent.")
        for (typed in listOf("La Marta és intel·ligent", "la marta és intel.ligent", "La Marta és intel-ligent")) {
            assertEquals(typed, Verdict.CORRECT, exercise.check(typed, Mode.TRANSLATE))
        }
        assertEquals(Verdict.WRONG, exercise.check("La Marta és inteligent", Mode.TRANSLATE))
    }

    @Test
    fun cedillaCountsAsAnAccent() {
        val exercise = Exercise("test", "","Vaig a la plaça.")
        assertEquals(Verdict.ACCENTS_ONLY, exercise.check("Vaig a la placa", Mode.TRANSLATE))
    }

    @Test
    fun everyLessonHasFifteenDistinctSentencesAndNotes() {
        for (lesson in courses.flatMap { it.lessons }) {
            assertEquals(lesson.title, 15, lesson.exercises.size)
            assertEquals(lesson.title, 15, lesson.exercises.map { it.answer }.toSet().size)
            assertFalse(lesson.title, lesson.explanation.isBlank())
        }
    }

    @Test
    fun everyModelAnswerIsWellFormedAndPassesBothModes() {
        for (lesson in courses.flatMap { it.lessons }) for (exercise in lesson.exercises) {
            val answer = exercise.answer
            assertFalse(answer, answer.any { it in "{}|" })
            assertFalse(answer, "  " in answer || answer != answer.trim())
            assertEquals(answer, Verdict.CORRECT, exercise.check(answer, Mode.TRANSLATE))
            assertEquals(answer, Verdict.CORRECT, exercise.check(answer, Mode.LISTEN))
        }
    }
}
