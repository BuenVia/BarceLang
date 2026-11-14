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
    fun speakingAcceptsAnyWordingButNotTheWrongWords() {
        assertEquals(Verdict.CORRECT, exercise.check("Yo quiero que tú vinieras", Mode.SPEAK))
        assertEquals(Verdict.WRONG, exercise.check("Quiero que vienes", Mode.SPEAK))
    }

    @Test
    fun speakingIgnoresWhatCannotBeHeard() {
        assertEquals(Verdict.CORRECT, Exercise("test", "", "Sí, té set.").check("si te set", Mode.SPEAK))
        assertEquals(Verdict.CORRECT, Exercise("test", "", "Vaig a la plaça.").check("vaig a la placa", Mode.SPEAK))
        assertEquals(Verdict.CORRECT, Exercise("test", "", "És intel·ligent.").check("és intelligent", Mode.SPEAK))
    }

    @Test
    fun speakingPassesIfAnyGuessMatches() {
        assertEquals(Verdict.CORRECT, exercise.checkSpoken(listOf("quiero que vienes", "quiero que vengas")))
        assertEquals(Verdict.WRONG, exercise.checkSpoken(listOf("quiero que vienes", "quiero que viene")))
        assertEquals(Verdict.WRONG, exercise.checkSpoken(emptyList()))
    }

    @Test
    fun spokenNumbersMayComeBackAsDigits() {
        val cases = mapOf(
            "{|Jo }tinc trenta-un anys." to "Tinc 31 anys",
            "{|Jo }tinc vint-i-dos anys." to "tinc 22 anys",
            "Tengo treinta y un años." to "Tengo 31 años",
            "Tengo veintidós años." to "tengo 22 años",
            "Para las ocho habré terminado." to "Para las 8:00 habré terminado",
            "Són dos quarts de quatre." to "Són 2 quarts de 4",
            "{|Jo }tinc dues cases." to "Tinc 2 cases",
        )
        for ((pattern, heard) in cases) {
            assertEquals(heard, Verdict.CORRECT, Exercise("test", "", pattern).check(heard, Mode.SPEAK))
        }
        assertEquals(Verdict.WRONG, Exercise("test", "", "Tinc trenta-un anys.").check("tinc 30 anys", Mode.SPEAK))
        // "Un" is still an article, so it isn't turned into a number on its own.
        assertEquals(Verdict.CORRECT, Exercise("test", "", "Vull un cafè.").check("vull un cafe", Mode.SPEAK))
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
            assertEquals(answer, Verdict.CORRECT, exercise.check(answer, Mode.SPEAK))
        }
    }
}
