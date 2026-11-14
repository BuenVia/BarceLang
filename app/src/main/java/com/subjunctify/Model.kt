package com.subjunctify

import java.text.Normalizer

/** How a sentence is practised: write it from English, transcribe it by ear, or say it from English. */
enum class Mode { TRANSLATE, LISTEN, SPEAK }

enum class Verdict { CORRECT, ACCENTS_ONLY, WRONG }

/** [id] is what saved progress refers to, so like [Exercise.id] it must never be changed or reused. */
class Lesson(
    val id: String,
    val title: String,
    val level: String,
    val explanation: String,
    val exercises: List<Exercise>,
    val category: String? = null,
)

/**
 * One practice sentence.
 *
 * [id] is what saved review progress refers to, so the wording can be corrected freely. Never change
 * or reuse an ID, even after deleting its exercise: a new exercise takes the next unused number.
 *
 * [pattern] holds the sentence in the language being taught, with `{a|b}` marking interchangeable wording. The first option of
 * each group forms the model answer, which is what gets displayed and spoken, so `{|tú }vengas`
 * shows "vengas" but also accepts "tú vengas". [alsoAccepted] covers rewordings that groups can't
 * express, such as swapping the two clauses of a sentence.
 */
class Exercise(val id: String, val english: String, pattern: String, vararg alsoAccepted: String) {
    private val expanded = expand(pattern)
    private val wordings = expanded + alsoAccepted.flatMap(::expand)

    val answer: String = expanded.first().capitalizeFirstLetter()

    private val translations: Set<String> = wordings.map(::normalize).toSet()

    private val spokenTranslations: Set<String> by lazy { wordings.map(::spoken).toSet() }

    /** Translating accepts any listed wording; transcribing must match what was actually said. */
    fun check(typed: String, mode: Mode): Verdict {
        if (mode == Mode.SPEAK) return checkSpoken(listOf(typed))
        val accepted = if (mode == Mode.TRANSLATE) translations else setOf(normalize(answer))
        val given = normalize(typed)
        return when {
            given in accepted -> Verdict.CORRECT
            accepted.any { stripAccents(it) == stripAccents(given) } -> Verdict.ACCENTS_ONLY
            else -> Verdict.WRONG
        }
    }

    /**
     * [heard] is the recogniser's guesses at one attempt, best first. Any listed wording passes, as
     * when translating, and it's enough for one guess to match, since the recogniser often ranks the
     * right sentence below a near miss. Speech has no spelling, so there's no [Verdict.ACCENTS_ONLY].
     */
    fun checkSpoken(heard: List<String>): Verdict =
        if (heard.any { spoken(it) in spokenTranslations }) Verdict.CORRECT else Verdict.WRONG
}

private val optionGroup = Regex("""\{([^{}]*)\}""")
private val punctuation = Regex("""[\p{Punct}¿¡«»…—–‘’“”]""")
private val whitespace = Regex("""\s+""")
private val combiningMarks = Regex("""\p{M}+""")

// Few keyboards have the Catalan "·", so "col.legi" is the usual way to type "col·legi".
private val typedGeminateL = Regex("""(?<=l)[.\-](?=l)""")

private fun expand(pattern: String): List<String> {
    val group = optionGroup.find(pattern) ?: return listOf(pattern)
    return group.groupValues[1].split('|').flatMap { expand(pattern.replaceRange(group.range, it)) }
}

private fun String.capitalizeFirstLetter(): String {
    val first = indexOfFirst(Char::isLetter)
    return if (first < 0) this else replaceRange(first, first + 1, this[first].uppercase())
}

/** Ignores case, punctuation and spacing. Accents stay significant: "hable" is not "hablé". */
private fun normalize(text: String): String =
    Normalizer.normalize(text, Normalizer.Form.NFC)
        .lowercase()
        .replace(typedGeminateL, "·")
        .replace(punctuation, " ")
        .replace(whitespace, " ")
        .trim()

private fun stripAccents(text: String): String =
    Normalizer.normalize(text, Normalizer.Form.NFD).replace(combiningMarks, "")

/**
 * Only what can be heard, as the recogniser writes it. Accents and the "·" go, because "si" and "sí"
 * sound alike, and numbers become digits on both sides, because "les vuit" may come back as "les 8".
 * That means a spoken "dos cases" passes for "dues cases": the recogniser would write "2" for either.
 */
private fun spoken(text: String): String =
    numbersAsDigits(stripAccents(normalize(text.replace(onTheHour, "$1"))).replace("·", ""))

/** "a las 8:00" is how recognisers often write "a las ocho". */
private val onTheHour = Regex("""(\d)[:.]00\b""")

/** Spanish and Catalan together, without accents. None of these means something else in the other. */
private val numberWords: Map<String, Int> = mapOf(
    "zero" to 0, "cero" to 0,
    "dos" to 2, "dues" to 2, "tres" to 3, "quatre" to 4, "cuatro" to 4, "cinc" to 5, "cinco" to 5,
    "sis" to 6, "seis" to 6, "set" to 7, "siete" to 7, "vuit" to 8, "ocho" to 8, "nou" to 9,
    "nueve" to 9, "deu" to 10, "diez" to 10, "onze" to 11, "once" to 11, "dotze" to 12, "doce" to 12,
    "tretze" to 13, "trece" to 13, "catorze" to 14, "catorce" to 14, "quinze" to 15, "quince" to 15,
    "setze" to 16, "dieciseis" to 16, "disset" to 17, "diecisiete" to 17, "divuit" to 18,
    "dieciocho" to 18, "dinou" to 19, "diecinueve" to 19,
    "vint" to 20, "veinte" to 20, "veintiun" to 21, "veintiuno" to 21, "veintiuna" to 21,
    "veintidos" to 22, "veintitres" to 23, "veinticuatro" to 24, "veinticinco" to 25,
    "veintiseis" to 26, "veintisiete" to 27, "veintiocho" to 28, "veintinueve" to 29,
    "trenta" to 30, "treinta" to 30, "quaranta" to 40, "cuarenta" to 40, "cinquanta" to 50,
    "cincuenta" to 50, "seixanta" to 60, "sesenta" to 60, "setanta" to 70, "setenta" to 70,
    "vuitanta" to 80, "ochenta" to 80, "noranta" to 90, "noventa" to 90, "cent" to 100, "cien" to 100,
)

/** "One" is also "a", so it only counts as a number after a ten, as in "trenta-un". */
private val ones = setOf("u", "un", "una", "uno")
private val tensJoiners = setOf("i", "y")

private fun numbersAsDigits(text: String): String {
    val words = text.split(' ').map { numberWords[it]?.toString() ?: it }
    val result = mutableListOf<String>()
    var i = 0
    while (i < words.size) {
        val tens = words[i].toIntOrNull()
        if (tens != null && tens in 20..90 && tens % 10 == 0) {
            var next = i + 1
            if (words.getOrNull(next) in tensJoiners) next++
            val unit = words.getOrNull(next)?.let { if (it in ones) 1 else it.toIntOrNull() }
            if (unit != null && unit in 1..9) {
                result += (tens + unit).toString()
                i = next + 1
                continue
            }
        }
        result += words[i]
        i++
    }
    return result.joinToString(" ")
}
