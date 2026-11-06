package com.subjunctify

import java.text.Normalizer

enum class Mode { TRANSLATE, LISTEN }

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

    val answer: String = expanded.first().capitalizeFirstLetter()

    private val translations: Set<String> =
        (expanded + alsoAccepted.flatMap(::expand)).map(::normalize).toSet()

    /** Translating accepts any listed wording; transcribing must match what was actually said. */
    fun check(typed: String, mode: Mode): Verdict {
        val accepted = if (mode == Mode.TRANSLATE) translations else setOf(normalize(answer))
        val given = normalize(typed)
        return when {
            given in accepted -> Verdict.CORRECT
            accepted.any { stripAccents(it) == stripAccents(given) } -> Verdict.ACCENTS_ONLY
            else -> Verdict.WRONG
        }
    }
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
