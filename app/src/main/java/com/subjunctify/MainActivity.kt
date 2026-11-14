package com.subjunctify

import android.Manifest
import android.content.pm.PackageManager
import android.graphics.Color as AndroidColor
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.SystemBarStyle
import androidx.activity.compose.BackHandler
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.clickable
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.consumeWindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.imePadding
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.LocalContentColor
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.key
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableLongStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.produceState
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberUpdatedState
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardCapitalization
import androidx.compose.ui.text.intl.LocaleList
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.unit.dp
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.compose.LocalLifecycleOwner
import androidx.lifecycle.repeatOnLifecycle
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import java.time.Instant
import java.time.ZoneId
import java.time.format.DateTimeFormatter
import java.time.format.FormatStyle
import kotlin.random.Random

private const val MIXED_ROUND_SIZE = 15

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        val preference = ThemePreference(this)
        setContent {
            var choice by remember { mutableStateOf(preference.load()) }
            val dark = choice ?: isSystemInDarkTheme()

            // By default the system bars follow the device, which would hide their icons whenever
            // the toggle disagrees with it.
            DisposableEffect(dark) {
                enableEdgeToEdge(
                    statusBarStyle = SystemBarStyle.auto(
                        AndroidColor.TRANSPARENT,
                        AndroidColor.TRANSPARENT,
                    ) { dark },
                    navigationBarStyle = SystemBarStyle.auto(
                        LIGHT_NAVIGATION_SCRIM,
                        DARK_NAVIGATION_SCRIM,
                    ) { dark },
                )
                onDispose {}
            }

            val theme = remember(dark) {
                ThemeState(dark) {
                    choice = !dark
                    preference.save(!dark)
                }
            }
            AppTheme(theme) { App() }
        }
    }
}

// The same scrims enableEdgeToEdge() uses by default. They only show on Android 9 and below.
private val LIGHT_NAVIGATION_SCRIM = AndroidColor.argb(0xE6, 0xFF, 0xFF, 0xFF)
private val DARK_NAVIGATION_SCRIM = AndroidColor.argb(0x80, 0x1B, 0x1B, 0x1B)

/**
 * Navigation is just these values: no course = the course list, a review queue = reviewing, no
 * lesson = the lesson list, no mode = the explanation. The queue is fixed when a review starts, so answers don't reshuffle it mid-session.
 */
@Composable
private fun App() {
    var courseIndex by rememberSaveable { mutableStateOf<Int?>(null) }
    var lessonIndex by rememberSaveable { mutableStateOf<Int?>(null) }
    var mode by rememberSaveable { mutableStateOf<Mode?>(null) }
    var reviewQueue by rememberSaveable { mutableStateOf<ArrayList<String>?>(null) }
    var completedOpen by rememberSaveable { mutableStateOf(false) }

    val context = LocalContext.current
    val store = remember { ReviewStore.get(context) }
    val onAnswered = { exercise: Exercise, answeredIn: Mode, passed: Boolean ->
        store.recordAnswer(exercise.id, answeredIn, passed)
    }
    // Collected here so returning to the lesson list doesn't briefly show every lesson as unstarted.
    val lessonRounds by store.lessonRounds.collectAsState(initial = null)
    val lessonStartTimes = remember(lessonRounds) { lessonRounds.orEmpty().lessonStartTimes() }

    val course = courseIndex?.let { courses[it] }
    if (course == null) {
        CourseListScreen(onOpen = { courseIndex = it })
        return
    }

    val lesson = lessonIndex?.let { course.lessons[it] }
    val activeMode = mode
    val queue = reviewQueue
    when {
        queue != null && activeMode != null -> PracticeScreen(
            title = "Review",
            exercises = remember(queue) { queue.mapNotNull(exercisesById::get) },
            language = course.language,
            mode = activeMode,
            onAnswered = onAnswered,
            canRetry = false,
            exitLabel = "Done",
            onExit = {
                reviewQueue = null
                mode = null
            },
        )
        // Rotation reloads the rounds. Waiting for them means a round in progress keeps its place,
        // because its restored position would otherwise point into an empty list of sentences.
        completedOpen && lessonRounds == null -> Unit
        completedOpen -> {
            val completed = remember(course, lessonStartTimes) { course.completedLessons(lessonStartTimes) }
            if (activeMode == null) {
                CompletedLessonsScreen(
                    lessons = completed,
                    language = course.language,
                    onBack = { completedOpen = false },
                    onPractise = { mode = it },
                )
            } else {
                PracticeScreen(
                    title = "Completed lessons",
                    exercises = remember(completed) { completed.flatMap { it.exercises } },
                    roundSize = MIXED_ROUND_SIZE,
                    language = course.language,
                    mode = activeMode,
                    onAnswered = onAnswered,
                    canRetry = true,
                    exitLabel = "Back to sentences",
                    onExit = { mode = null },
                )
            }
        }
        lesson == null -> LessonListScreen(
            course = course,
            cards = store.cards,
            lessonStartTimes = lessonStartTimes,
            onOpenCompleted = { completedOpen = true },
            onBack = { courseIndex = null },
            onOpen = { lessonIndex = it },
            onReview = { reviewMode, exerciseIds ->
                mode = reviewMode
                reviewQueue = ArrayList(exerciseIds)
            },
        )
        activeMode == null -> LessonScreen(
            lesson = lesson,
            language = course.language,
            onBack = { lessonIndex = null },
            onPractise = { mode = it },
        )
        else -> PracticeScreen(
            title = lesson.title,
            exercises = lesson.exercises,
            language = course.language,
            mode = activeMode,
            onAnswered = onAnswered,
            onRoundFinished = { store.recordRoundFinished(lesson.id, activeMode) },
            canRetry = true,
            exitLabel = "Back to lesson",
            onExit = { mode = null },
        )
    }
}

/** The current time, kept fresh enough that cards fall due while the screen is open. */
@Composable
private fun currentTime(): Instant {
    val lifecycle = LocalLifecycleOwner.current.lifecycle
    val time by produceState(Instant.now(), lifecycle) {
        lifecycle.repeatOnLifecycle(Lifecycle.State.RESUMED) {
            while (true) {
                value = Instant.now()
                delay(60_000)
            }
        }
    }
    return time
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun AppScaffold(
    title: String,
    onBack: (() -> Unit)? = null,
    bottomBar: @Composable () -> Unit = {},
    content: @Composable (PaddingValues) -> Unit,
) {
    if (onBack != null) BackHandler(onBack = onBack)
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(title) },
                navigationIcon = {
                    if (onBack != null) TextButton(onClick = onBack) { Text("← Back") }
                },
                actions = { ThemeToggle() },
            )
        },
        bottomBar = bottomBar,
        content = content,
    )
}

@Composable
private fun ThemeToggle() {
    val theme = LocalThemeState.current
    IconButton(onClick = theme.toggle) {
        Icon(
            painter = painterResource(if (theme.isDark) R.drawable.ic_light_mode else R.drawable.ic_dark_mode),
            contentDescription = if (theme.isDark) "Switch to light mode" else "Switch to dark mode",
        )
    }
}

@Composable
private fun CourseListScreen(onOpen: (Int) -> Unit) {
    AppScaffold(title = "CatLang") { padding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
                .verticalScroll(rememberScrollState())
                .padding(horizontal = 16.dp, vertical = 8.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp),
        ) {
            Text("What would you like to practise?", style = MaterialTheme.typography.bodyLarge)
            courses.forEachIndexed { index, course ->
                Card(Modifier.fillMaxWidth().clickable { onOpen(index) }) {
                    Column(
                        modifier = Modifier.padding(16.dp),
                        verticalArrangement = Arrangement.spacedBy(4.dp),
                    ) {
                        Row(verticalAlignment = Alignment.CenterVertically) {
                            Text(
                                text = course.title,
                                style = MaterialTheme.typography.titleLarge,
                                modifier = Modifier.weight(1f),
                            )
                            Text(
                                text = course.level,
                                style = MaterialTheme.typography.labelLarge,
                                color = MaterialTheme.colorScheme.primary,
                                modifier = Modifier.padding(start = 12.dp),
                            )
                        }
                        Text(course.description, style = MaterialTheme.typography.bodyMedium)
                        Text(
                            text = "${course.lessons.size} subjects",
                            style = MaterialTheme.typography.labelMedium,
                        )
                    }
                }
            }
        }
    }
}

@Composable
private fun LessonListScreen(
    course: Course,
    cards: Flow<List<ReviewCard>>,
    lessonStartTimes: Map<String, Instant>,
    onOpenCompleted: () -> Unit,
    onBack: () -> Unit,
    onOpen: (Int) -> Unit,
    onReview: (Mode, List<String>) -> Unit,
) {
    val loadedCards by cards.collectAsState(initial = null)
    AppScaffold(title = "${course.title} ${course.level}", onBack = onBack) { padding ->
        // Outside the list, so the panel growing when reviews fall due can't scroll the lessons.
        Column(Modifier.fillMaxSize().padding(top = padding.calculateTopPadding() + 8.dp)) {
            CompletedLessonsCard(
                completedCount = course.completedLessons(lessonStartTimes).size,
                onClick = onOpenCompleted,
                modifier = Modifier.padding(start = 16.dp, end = 16.dp, bottom = 8.dp),
            )
            loadedCards?.let {
                ReviewPanel(
                    course = course,
                    cards = it.inCourse(course),
                    onReview = onReview,
                    modifier = Modifier.padding(horizontal = 16.dp),
                )
            }
            LazyColumn(
                modifier = Modifier.fillMaxWidth().weight(1f),
                contentPadding = PaddingValues(
                    start = 16.dp,
                    end = 16.dp,
                    top = 8.dp,
                    bottom = padding.calculateBottomPadding() + 16.dp,
                ),
                verticalArrangement = Arrangement.spacedBy(8.dp),
            ) {
                itemsIndexed(course.lessons) { index, lesson ->
                    val startedAt = lessonStartTimes[lesson.id]
                    Card(
                        modifier = Modifier.fillMaxWidth().clickable { onOpen(index) },
                        colors = if (startedAt == null) {
                            CardDefaults.cardColors()
                        } else {
                            CardDefaults.cardColors(
                                containerColor = MaterialTheme.colorScheme.tertiaryContainer,
                                contentColor = MaterialTheme.colorScheme.onTertiaryContainer,
                            )
                        },
                    ) {
                        Row(
                            modifier = Modifier.padding(16.dp),
                            verticalAlignment = Alignment.CenterVertically,
                        ) {
                            Column(Modifier.weight(1f)) {
                                Text(lesson.title, style = MaterialTheme.typography.titleMedium)
                                if (startedAt != null) {
                                    Text(
                                        text = "Started ${formatDate(startedAt)}",
                                        style = MaterialTheme.typography.labelMedium,
                                        modifier = Modifier.padding(top = 4.dp),
                                    )
                                }
                            }
                            // Some category names are long, so they wrap beside the title.
                            Column(
                                modifier = Modifier
                                    .padding(start = 12.dp)
                                    .then(if (lesson.category != null) Modifier.weight(0.6f) else Modifier),
                                horizontalAlignment = Alignment.End,
                            ) {
                                lesson.category?.let {
                                    Text(
                                        text = it,
                                        style = MaterialTheme.typography.labelMedium,
                                        textAlign = TextAlign.End,
                                    )
                                }
                                Text(
                                    text = lesson.level,
                                    style = MaterialTheme.typography.labelLarge,
                                    color = if (startedAt == null) MaterialTheme.colorScheme.primary else LocalContentColor.current,
                                )
                            }
                        }
                    }
                }
            }
        }
    }
}

@Composable
private fun CompletedLessonsCard(completedCount: Int, onClick: () -> Unit, modifier: Modifier = Modifier) {
    Card(
        modifier = modifier.fillMaxWidth().clickable(onClick = onClick),
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.secondaryContainer,
            contentColor = MaterialTheme.colorScheme.onSecondaryContainer,
        ),
    ) {
        Row(
            modifier = Modifier.padding(horizontal = 16.dp, vertical = 12.dp),
            verticalAlignment = Alignment.CenterVertically,
        ) {
            Column(Modifier.weight(1f)) {
                Text("Completed lessons", style = MaterialTheme.typography.titleMedium)
                Text(
                    text = when (completedCount) {
                        0 -> "None yet"
                        1 -> "Mixed practice from 1 lesson"
                        else -> "Mixed practice from $completedCount lessons"
                    },
                    style = MaterialTheme.typography.bodySmall,
                )
            }
            Text("→", style = MaterialTheme.typography.titleMedium)
        }
    }
}

@Composable
private fun CompletedLessonsScreen(
    lessons: List<Lesson>,
    language: Language,
    onBack: () -> Unit,
    onPractise: (Mode) -> Unit,
) {
    AppScaffold(
        title = "Completed lessons",
        onBack = onBack,
        bottomBar = {
            if (lessons.isNotEmpty()) {
                Column(
                    modifier = Modifier.navigationBarsPadding().padding(16.dp),
                    verticalArrangement = Arrangement.spacedBy(8.dp),
                ) {
                    Text(
                        text = "Practise $MIXED_ROUND_SIZE random sentences",
                        style = MaterialTheme.typography.labelLarge,
                    )
                    PractiseButtons(language, onPractise)
                }
            }
        },
    ) { padding ->
        if (lessons.isEmpty()) {
            Text(
                text = "Finish a Reading round and a Hearing round of a lesson, " +
                    "and its sentences will appear here.",
                style = MaterialTheme.typography.bodyLarge,
                modifier = Modifier.padding(padding).padding(16.dp),
            )
        } else {
            LazyColumn(
                modifier = Modifier.fillMaxSize().padding(padding),
                contentPadding = PaddingValues(horizontal = 16.dp, vertical = 8.dp),
                verticalArrangement = Arrangement.spacedBy(8.dp),
            ) {
                for (lesson in lessons) {
                    item(key = lesson.id) {
                        Text(
                            text = lesson.title,
                            style = MaterialTheme.typography.titleMedium,
                            color = MaterialTheme.colorScheme.primary,
                            modifier = Modifier.padding(top = 8.dp),
                        )
                    }
                    items(lesson.exercises, key = { it.id }) { exercise ->
                        Column {
                            Text(exercise.answer, fontWeight = FontWeight.Bold)
                            Text(exercise.english, style = MaterialTheme.typography.bodyMedium)
                        }
                    }
                }
            }
        }
    }
}

/** A date in the device's own format, such as "6 Nov 2026". */
private fun formatDate(time: Instant): String =
    time.atZone(ZoneId.systemDefault()).toLocalDate()
        .format(DateTimeFormatter.ofLocalizedDate(FormatStyle.MEDIUM))

@Composable
private fun ReviewPanel(
    course: Course,
    cards: List<ReviewCard>,
    onReview: (Mode, List<String>) -> Unit,
    modifier: Modifier = Modifier,
) {
    val now = currentTime()
    val translateDue = cards.due(Mode.TRANSLATE, now).size
    val listenDue = cards.due(Mode.LISTEN, now).size
    val speakDue = cards.due(Mode.SPEAK, now).size
    val totalDue = translateDue + listenDue + speakDue

    Card(
        modifier = modifier.fillMaxWidth(),
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.primaryContainer,
            contentColor = MaterialTheme.colorScheme.onPrimaryContainer,
        ),
    ) {
        Column(Modifier.padding(16.dp), verticalArrangement = Arrangement.spacedBy(8.dp)) {
            Text("Review", style = MaterialTheme.typography.titleMedium)
            when {
                totalDue > 0 -> {
                    Text(if (totalDue == 1) "1 review due" else "$totalDue reviews due")
                    if (translateDue > 0) {
                        Button(
                            onClick = { onReview(Mode.TRANSLATE, cards.nextSession(Mode.TRANSLATE, now)) },
                            modifier = Modifier.fillMaxWidth(),
                        ) { Text("Write ${course.language.displayName} ($translateDue)") }
                    }
                    if (listenDue > 0) {
                        OutlinedButton(
                            onClick = { onReview(Mode.LISTEN, cards.nextSession(Mode.LISTEN, now)) },
                            modifier = Modifier.fillMaxWidth(),
                        ) { Text("Transcribe ${course.language.displayName} ($listenDue)") }
                    }
                    if (speakDue > 0) {
                        OutlinedButton(
                            onClick = { onReview(Mode.SPEAK, cards.nextSession(Mode.SPEAK, now)) },
                            modifier = Modifier.fillMaxWidth(),
                        ) { Text("Speak ${course.language.displayName} ($speakDue)") }
                    }
                }
                cards.isNotEmpty() -> Text(
                    "All caught up. Next review ${cards.nextReview(now, ZoneId.systemDefault())}.",
                )
                else -> Text("Practise a lesson and its sentences will come back here to review.")
            }
        }
    }
}

@Composable
private fun LessonScreen(
    lesson: Lesson,
    language: Language,
    onBack: () -> Unit,
    onPractise: (Mode) -> Unit,
) {
    AppScaffold(
        title = lesson.level,
        onBack = onBack,
        bottomBar = {
            Column(
                modifier = Modifier.navigationBarsPadding().padding(16.dp),
                verticalArrangement = Arrangement.spacedBy(8.dp),
            ) {
                Text(
                    text = "Practise ${lesson.exercises.size} sentences",
                    style = MaterialTheme.typography.labelLarge,
                )
                PractiseButtons(language, onPractise)
            }
        },
    ) { padding ->
        Column(
            Modifier
                .fillMaxSize()
                .padding(padding)
                .verticalScroll(rememberScrollState())
                .padding(horizontal = 16.dp, vertical = 8.dp),
        ) {
            Text(lesson.title, style = MaterialTheme.typography.headlineSmall)
            Spacer(Modifier.height(8.dp))
            Explanation(lesson.explanation)
        }
    }
}

/** One button per mode. Speaking is left out on devices that can't recognise speech. */
@Composable
private fun PractiseButtons(language: Language, onPractise: (Mode) -> Unit) {
    val context = LocalContext.current
    val canSpeak = remember { canRecognizeSpeech(context) }
    Button(onClick = { onPractise(Mode.TRANSLATE) }, Modifier.fillMaxWidth()) {
        Text("Read English, write ${language.displayName}")
    }
    OutlinedButton(onClick = { onPractise(Mode.LISTEN) }, Modifier.fillMaxWidth()) {
        Text("Hear ${language.displayName}, transcribe it")
    }
    if (canSpeak) {
        OutlinedButton(onClick = { onPractise(Mode.SPEAK) }, Modifier.fillMaxWidth()) {
            Text("Read English, say it in ${language.displayName}")
        }
    }
}

/** Renders lesson notes: `## ` is a heading, `| ` is a table row, `- ` a bullet, `**x**` is bold. */
@Composable
private fun Explanation(text: String) {
    for (line in text.lines()) {
        when {
            line.isBlank() -> Spacer(Modifier.height(8.dp))
            line.startsWith("## ") -> Text(
                text = line.removePrefix("## "),
                style = MaterialTheme.typography.titleMedium,
                color = MaterialTheme.colorScheme.primary,
                modifier = Modifier.padding(top = 8.dp, bottom = 4.dp),
            )
            line.startsWith("| ") -> Text(
                text = line.removePrefix("| "),
                style = MaterialTheme.typography.bodySmall,
                fontFamily = FontFamily.Monospace,
            )
            line.startsWith("- ") -> Text(
                text = withBold("•  " + line.removePrefix("- ")),
                style = MaterialTheme.typography.bodyMedium,
                modifier = Modifier.padding(start = 8.dp, bottom = 2.dp),
            )
            else -> Text(withBold(line), style = MaterialTheme.typography.bodyMedium)
        }
    }
}

private fun withBold(text: String): AnnotatedString = buildAnnotatedString {
    text.split("**").forEachIndexed { index, part ->
        if (index % 2 == 1) withStyle(SpanStyle(fontWeight = FontWeight.Bold)) { append(part) }
        else append(part)
    }
}

@Composable
private fun PracticeScreen(
    title: String,
    exercises: List<Exercise>,
    roundSize: Int = Int.MAX_VALUE,
    language: Language,
    mode: Mode,
    onAnswered: (Exercise, Mode, Boolean) -> Unit,
    onRoundFinished: () -> Unit = {},
    canRetry: Boolean,
    exitLabel: String,
    onExit: () -> Unit,
) {
    var index by rememberSaveable { mutableIntStateOf(0) }
    var score by rememberSaveable { mutableIntStateOf(0) }
    var answer by rememberSaveable { mutableStateOf("") }
    var verdict by rememberSaveable { mutableStateOf<Verdict?>(null) }

    // Saved as a seed so that rotating the screen keeps the order, and with it the place in the round.
    var shuffleSeed by rememberSaveable { mutableLongStateOf(Random.nextLong()) }
    val shuffled = remember(exercises, shuffleSeed) { exercises.shuffled(Random(shuffleSeed)).take(roundSize) }

    val total = shuffled.size
    if (index == total) {
        ResultScreen(
            title = title,
            score = score,
            total = total,
            onRetry = if (canRetry) {
                {
                    index = 0
                    score = 0
                    shuffleSeed = Random.nextLong()
                }
            } else {
                null
            },
            exitLabel = exitLabel,
            onExit = onExit,
        )
        return
    }

    val exercise = shuffled[index]
    val focusManager = LocalFocusManager.current
    val focus = remember { FocusRequester() }

    // Created with the round rather than the feedback, so the voice is ready by the first answer.
    val context = LocalContext.current
    val speaker = if (mode == Mode.SPEAK) remember { Speaker(context, language) } else null
    if (speaker != null) {
        DisposableEffect(speaker) {
            onDispose { speaker.shutdown() }
        }
    }
    val grade = { result: Verdict ->
        verdict = result
        if (result == Verdict.CORRECT) score++
        onAnswered(exercise, mode, result == Verdict.CORRECT)
        if (index + 1 == total) onRoundFinished()
    }
    val submit = {
        if (answer.isNotBlank() && verdict == null) {
            grade(exercise.check(answer, mode))
            focusManager.clearFocus()
        }
    }
    // Shows the guess that passed, or otherwise the recogniser's best one.
    val submitSpoken = { heard: List<String> ->
        if (verdict == null) {
            val result = exercise.checkSpoken(heard)
            answer = heard.firstOrNull { exercise.checkSpoken(listOf(it)) == Verdict.CORRECT } ?: heard.first()
            grade(result)
        }
    }

    // Keyboard opens for each new question, unless the current one has already been answered.
    // Speaking has no text field to focus.
    LaunchedEffect(index) {
        if (verdict == null && mode != Mode.SPEAK) focus.requestFocus()
    }

    AppScaffold(title = title, onBack = onExit) { padding ->
        Column(
            Modifier
                .fillMaxSize()
                .consumeWindowInsets(padding)
                .padding(padding)
                .imePadding()
                .verticalScroll(rememberScrollState())
                .padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(12.dp),
        ) {
            Text("Sentence ${index + 1} of $total", style = MaterialTheme.typography.labelLarge)
            LinearProgressIndicator(
                progress = { index.toFloat() / total },
                modifier = Modifier.fillMaxWidth(),
            )

            when (mode) {
                Mode.TRANSLATE -> TranslatePrompt(exercise, language)
                Mode.LISTEN -> ListenPrompt(exercise, language, autoPlay = verdict == null)
                // A fresh listener for each sentence, so a problem with the last one doesn't linger.
                Mode.SPEAK -> key(exercise.id) {
                    SpeakPrompt(exercise, language, answered = verdict != null, onHeard = submitSpoken)
                }
            }

            if (mode != Mode.SPEAK) OutlinedTextField(
                value = answer,
                // The field wraps long sentences, so Enter would otherwise add a new line.
                onValueChange = { if ('\n' in it) submit() else answer = it },
                label = { Text("Your answer in ${language.displayName}") },
                readOnly = verdict != null,
                minLines = 2,
                keyboardOptions = KeyboardOptions(
                    capitalization = KeyboardCapitalization.Sentences,
                    autoCorrectEnabled = false,
                    imeAction = ImeAction.Done,
                    hintLocales = LocaleList(language.localeTag),
                ),
                keyboardActions = KeyboardActions(onDone = { submit() }),
                modifier = Modifier.fillMaxWidth().focusRequester(focus),
            )

            val result = verdict
            if (result == null) {
                if (mode != Mode.SPEAK) Button(
                    onClick = submit,
                    enabled = answer.isNotBlank(),
                    modifier = Modifier.fillMaxWidth(),
                ) { Text("Submit") }
            } else {
                Feedback(result, exercise, heard = answer.takeIf { mode == Mode.SPEAK }, speaker = speaker)
                Button(
                    onClick = {
                        index++
                        answer = ""
                        verdict = null
                    },
                    modifier = Modifier.fillMaxWidth(),
                ) { Text(if (index + 1 == total) "See results" else "Next sentence") }
            }
        }
    }
}

@Composable
private fun TranslatePrompt(exercise: Exercise, language: Language) {
    Card(Modifier.fillMaxWidth()) {
        Column(Modifier.padding(16.dp)) {
            Text("Translate into ${language.displayName}", style = MaterialTheme.typography.labelMedium)
            Spacer(Modifier.height(4.dp))
            Text(exercise.english, style = MaterialTheme.typography.titleLarge)
        }
    }
}

@Composable
private fun ListenPrompt(exercise: Exercise, language: Language, autoPlay: Boolean) {
    val context = LocalContext.current
    val speaker = remember { Speaker(context, language) }
    DisposableEffect(speaker) {
        onDispose { speaker.shutdown() }
    }
    LaunchedEffect(exercise, speaker.status) {
        if (autoPlay && speaker.status == VoiceStatus.READY) speaker.speak(exercise.answer)
    }

    Card(Modifier.fillMaxWidth()) {
        Column(Modifier.padding(16.dp), verticalArrangement = Arrangement.spacedBy(8.dp)) {
            Text("Write what you hear", style = MaterialTheme.typography.labelMedium)
            if (speaker.status == VoiceStatus.UNAVAILABLE) {
                Text(
                    "No ${language.voiceName} voice is available. Install \"${language.voiceSetting}\" in your " +
                        "device's text-to-speech settings, then reopen this exercise.",
                    color = MaterialTheme.colorScheme.error,
                )
            } else {
                val ready = speaker.status == VoiceStatus.READY
                Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                    Button(
                        onClick = { speaker.speak(exercise.answer) },
                        enabled = ready,
                        modifier = Modifier.weight(1f),
                    ) { Text("▶ Play") }
                    OutlinedButton(
                        onClick = { speaker.speak(exercise.answer, slowly = true) },
                        enabled = ready,
                        modifier = Modifier.weight(1f),
                    ) { Text("▶ Slowly") }
                }
            }
        }
    }
}

@Composable
private fun SpeakPrompt(exercise: Exercise, language: Language, answered: Boolean, onHeard: (List<String>) -> Unit) {
    val context = LocalContext.current
    val available = remember { canRecognizeSpeech(context) }
    val currentOnHeard by rememberUpdatedState(onHeard)
    val listener = remember { Listener(context, language) { currentOnHeard(it) } }
    DisposableEffect(listener) {
        onDispose { listener.shutdown() }
    }

    var permissionDenied by remember { mutableStateOf(false) }
    val requestPermission = rememberLauncherForActivityResult(ActivityResultContracts.RequestPermission()) { granted ->
        permissionDenied = !granted
        if (granted) listener.start()
    }
    val startListening = {
        if (context.checkSelfPermission(Manifest.permission.RECORD_AUDIO) == PackageManager.PERMISSION_GRANTED) {
            listener.start()
        } else {
            requestPermission.launch(Manifest.permission.RECORD_AUDIO)
        }
    }

    Card(Modifier.fillMaxWidth()) {
        Column(Modifier.padding(16.dp), verticalArrangement = Arrangement.spacedBy(8.dp)) {
            Text("Say it in ${language.displayName}", style = MaterialTheme.typography.labelMedium)
            Text(exercise.english, style = MaterialTheme.typography.titleLarge)
            when {
                answered -> Unit
                !available -> Text(
                    "This device has no speech recognition service, so speaking exercises can't be marked.",
                    color = MaterialTheme.colorScheme.error,
                )
                listener.isListening -> {
                    Text(
                        text = listener.partial.ifEmpty { "Listening…" },
                        style = MaterialTheme.typography.bodyLarge,
                    )
                    OutlinedButton(onClick = listener::stop, Modifier.fillMaxWidth()) { Text("■ Done speaking") }
                }
                else -> {
                    val problem = if (permissionDenied) {
                        "CatLang needs microphone access to hear you. Allow it when asked, or in the app's settings."
                    } else {
                        listener.problem
                    }
                    problem?.let { Text(it, color = MaterialTheme.colorScheme.error) }
                    Button(onClick = startListening, Modifier.fillMaxWidth()) { Text("🎤 Speak") }
                }
            }
        }
    }
}

/**
 * [heard] is what the recogniser made of a spoken answer, shown so a miss can be understood. With a
 * [speaker], the model answer can be played to compare with how it was said.
 */
@Composable
private fun Feedback(verdict: Verdict, exercise: Exercise, heard: String? = null, speaker: Speaker? = null) {
    val isCorrect = verdict == Verdict.CORRECT
    val colors = MaterialTheme.colorScheme
    val container = if (isCorrect) colors.tertiaryContainer else colors.errorContainer
    val content = if (isCorrect) colors.onTertiaryContainer else colors.onErrorContainer
    Card(
        modifier = Modifier.fillMaxWidth(),
        colors = CardDefaults.cardColors(containerColor = container, contentColor = content),
    ) {
        Column(Modifier.padding(16.dp), verticalArrangement = Arrangement.spacedBy(4.dp)) {
            Text(
                text = if (isCorrect) "✓ Correct" else "✗ Incorrect",
                style = MaterialTheme.typography.titleMedium,
            )
            if (verdict == Verdict.ACCENTS_ONLY) {
                Text("Check your accents: only those are different.")
            }
            if (heard != null) Text("Heard: “$heard”")
            Text(exercise.answer, fontWeight = FontWeight.Bold)
            Text(exercise.english)
            // Hidden without a voice: the Hearing exercise already explains how to install one.
            if (speaker != null && speaker.status != VoiceStatus.UNAVAILABLE) {
                OutlinedButton(
                    onClick = { speaker.speak(exercise.answer) },
                    enabled = speaker.status == VoiceStatus.READY,
                    colors = ButtonDefaults.outlinedButtonColors(contentColor = content),
                    modifier = Modifier.fillMaxWidth(),
                ) { Text("▶ Hear it") }
            }
        }
    }
}

@Composable
private fun ResultScreen(
    title: String,
    score: Int,
    total: Int,
    onRetry: (() -> Unit)?,
    exitLabel: String,
    onExit: () -> Unit,
) {
    AppScaffold(title = title, onBack = onExit) { padding ->
        Column(
            modifier = Modifier.fillMaxSize().padding(padding).padding(24.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(12.dp, Alignment.CenterVertically),
        ) {
            Text("$score / $total", style = MaterialTheme.typography.displayMedium)
            Text("sentences correct", style = MaterialTheme.typography.bodyLarge)
            Spacer(Modifier.height(12.dp))
            if (onRetry != null) {
                Button(onClick = onRetry, Modifier.fillMaxWidth()) { Text("Practise again") }
                OutlinedButton(onClick = onExit, Modifier.fillMaxWidth()) { Text(exitLabel) }
            } else {
                Button(onClick = onExit, Modifier.fillMaxWidth()) { Text(exitLabel) }
            }
        }
    }
}
