package com.subjunctify

/**
 * The language a course teaches, which sets the voice, the keyboard and the wording of its screens.
 * Spanish must have a voice for Spain, because the app teaches Castilian. Catalan isn't tied to a
 * region, so any Catalan voice will do.
 */
enum class Language(
    val displayName: String,
    val localeTag: String,
    val voiceName: String,
    val voiceSetting: String,
    val requiresRegionalVoice: Boolean,
) {
    SPANISH("Spanish", "es-ES", "Castilian Spanish", "Spanish (Spain)", true),
    CATALAN("Catalan", "ca-ES", "Catalan", "Catalan", false),
}

class Course(
    val title: String,
    val level: String,
    val description: String,
    val language: Language,
    val lessons: List<Lesson>,
) {
    val sentences: Set<String> = lessons.flatMap { it.exercises }.map { it.answer }.toSet()
}

/** The choices on the opening screen, in the order they're shown. */
val courses: List<Course> = listOf(
    Course(
        title = "Spanish grammar",
        level = "B2",
        description = "Tenses, conditions, connectors and more",
        language = Language.SPANISH,
        lessons = spanishB2Lessons,
    ),
    Course(
        title = "The Spanish subjunctive",
        level = "B1–C1",
        description = "From the present subjunctive to giving opinions",
        language = Language.SPANISH,
        lessons = subjunctiveLessons,
    ),
    Course(
        title = "Catalan",
        level = "A1",
        description = "First steps, from greetings to everyday verbs",
        language = Language.CATALAN,
        lessons = catalanA1Lessons,
    ),
)
