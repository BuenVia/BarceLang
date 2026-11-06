package com.subjunctify

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.BackHandler
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
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
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.produceState
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalFocusManager
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

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            AppTheme { App() }
        }
    }
}

@Composable
private fun AppTheme(content: @Composable () -> Unit) {
    val colors = if (isSystemInDarkTheme()) {
        darkColorScheme(
            primary = Color(0xFFFFB59E),
            onPrimary = Color(0xFF5B1A05),
            primaryContainer = Color(0xFF7B2E14),
            onPrimaryContainer = Color(0xFFFFDBD0),
        )
    } else {
        lightColorScheme(
            primary = Color(0xFF9C3D1F),
            onPrimary = Color.White,
            primaryContainer = Color(0xFFFFDBD0),
            onPrimaryContainer = Color(0xFF3A0B00),
        )
    }
    MaterialTheme(colorScheme = colors, content = content)
}

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

    val context = LocalContext.current
    val store = remember { ReviewStore.get(context) }
    val onAnswered = { exercise: Exercise, answeredIn: Mode, passed: Boolean ->
        store.recordAnswer(exercise.id, answeredIn, passed)
    }

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
        lesson == null -> LessonListScreen(
            course = course,
            cards = store.cards,
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
            )
        },
        bottomBar = bottomBar,
        content = content,
    )
}

@Composable
private fun CourseListScreen(onOpen: (Int) -> Unit) {
    AppScaffold(title = "Subjunctify") { padding ->
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
    onBack: () -> Unit,
    onOpen: (Int) -> Unit,
    onReview: (Mode, List<String>) -> Unit,
) {
    val loadedCards by cards.collectAsState(initial = null)
    AppScaffold(title = "${course.title} ${course.level}", onBack = onBack) { padding ->
        // Outside the list, so the panel growing when reviews fall due can't scroll the lessons.
        Column(Modifier.fillMaxSize().padding(top = padding.calculateTopPadding() + 8.dp)) {
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
                    Card(Modifier.fillMaxWidth().clickable { onOpen(index) }) {
                        Row(
                            modifier = Modifier.padding(16.dp),
                            verticalAlignment = Alignment.CenterVertically,
                        ) {
                            Text(
                                text = lesson.title,
                                style = MaterialTheme.typography.titleMedium,
                                modifier = Modifier.weight(1f),
                            )
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
                                    color = MaterialTheme.colorScheme.primary,
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
private fun ReviewPanel(
    course: Course,
    cards: List<ReviewCard>,
    onReview: (Mode, List<String>) -> Unit,
    modifier: Modifier = Modifier,
) {
    val now = currentTime()
    val translateDue = cards.due(Mode.TRANSLATE, now).size
    val listenDue = cards.due(Mode.LISTEN, now).size
    val totalDue = translateDue + listenDue

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
                Button(onClick = { onPractise(Mode.TRANSLATE) }, Modifier.fillMaxWidth()) {
                    Text("Read English, write ${language.displayName}")
                }
                OutlinedButton(onClick = { onPractise(Mode.LISTEN) }, Modifier.fillMaxWidth()) {
                    Text("Hear ${language.displayName}, transcribe it")
                }
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
    language: Language,
    mode: Mode,
    onAnswered: (Exercise, Mode, Boolean) -> Unit,
    canRetry: Boolean,
    exitLabel: String,
    onExit: () -> Unit,
) {
    var index by rememberSaveable { mutableIntStateOf(0) }
    var score by rememberSaveable { mutableIntStateOf(0) }
    var answer by rememberSaveable { mutableStateOf("") }
    var verdict by rememberSaveable { mutableStateOf<Verdict?>(null) }

    val total = exercises.size
    if (index == total) {
        ResultScreen(
            title = title,
            score = score,
            total = total,
            onRetry = if (canRetry) {
                {
                    index = 0
                    score = 0
                }
            } else {
                null
            },
            exitLabel = exitLabel,
            onExit = onExit,
        )
        return
    }

    val exercise = exercises[index]
    val focusManager = LocalFocusManager.current
    val focus = remember { FocusRequester() }
    val submit = {
        if (answer.isNotBlank() && verdict == null) {
            val result = exercise.check(answer, mode)
            verdict = result
            if (result == Verdict.CORRECT) score++
            onAnswered(exercise, mode, result == Verdict.CORRECT)
            focusManager.clearFocus()
        }
    }

    // Keyboard opens for each new question, unless the current one has already been answered.
    LaunchedEffect(index) {
        if (verdict == null) focus.requestFocus()
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
            }

            OutlinedTextField(
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
                Button(
                    onClick = submit,
                    enabled = answer.isNotBlank(),
                    modifier = Modifier.fillMaxWidth(),
                ) { Text("Submit") }
            } else {
                Feedback(result, exercise)
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
private fun Feedback(verdict: Verdict, exercise: Exercise) {
    val isCorrect = verdict == Verdict.CORRECT
    val dark = isSystemInDarkTheme()
    val container = when {
        !isCorrect -> MaterialTheme.colorScheme.errorContainer
        dark -> Color(0xFF143D1E)
        else -> Color(0xFFD8F0DC)
    }
    val content = when {
        !isCorrect -> MaterialTheme.colorScheme.onErrorContainer
        dark -> Color(0xFFBFEBC8)
        else -> Color(0xFF0F4D1E)
    }
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
            Text(exercise.answer, fontWeight = FontWeight.Bold)
            Text(exercise.english)
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
