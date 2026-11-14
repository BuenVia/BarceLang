package com.subjunctify

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.speech.RecognitionListener
import android.speech.RecognizerIntent
import android.speech.SpeechRecognizer
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue

private const val MAXIMUM_GUESSES = 5

/** Whether the device has a speech recognition service at all. Which languages it knows shows only when listening. */
fun canRecognizeSpeech(context: Context): Boolean = SpeechRecognizer.isRecognitionAvailable(context)

/**
 * Hears one attempt at a time in [language] through the device's speech recognition service, and
 * passes the guesses at what was said, best first, to [onHeard]. Use from the main thread, and only
 * once the app has the microphone permission.
 */
class Listener(context: Context, private val language: Language, private val onHeard: (List<String>) -> Unit) {
    var isListening by mutableStateOf(false)
        private set

    /** The words recognised so far in the current attempt. */
    var partial by mutableStateOf("")
        private set

    /** Why the last attempt heard nothing, so it can be tried again without counting as an answer. */
    var problem by mutableStateOf<String?>(null)
        private set

    private val recognizer = SpeechRecognizer.createSpeechRecognizer(context.applicationContext)

    init {
        recognizer.setRecognitionListener(object : RecognitionListener {
            override fun onResults(results: Bundle) {
                if (!isListening) return
                isListening = false
                val guesses = results.getStringArrayList(SpeechRecognizer.RESULTS_RECOGNITION)
                    .orEmpty()
                    .filter { it.isNotBlank() }
                if (guesses.isEmpty()) problem = NOT_HEARD else onHeard(guesses)
            }

            override fun onPartialResults(partialResults: Bundle) {
                partialResults.getStringArrayList(SpeechRecognizer.RESULTS_RECOGNITION)
                    ?.firstOrNull()
                    ?.let { partial = it }
            }

            // Some services report an error after their results, or after being stopped.
            override fun onError(error: Int) {
                if (!isListening) return
                isListening = false
                problem = describe(error)
            }

            override fun onReadyForSpeech(params: Bundle?) {}
            override fun onBeginningOfSpeech() {}
            override fun onRmsChanged(rmsdB: Float) {}
            override fun onBufferReceived(buffer: ByteArray?) {}
            override fun onEndOfSpeech() {}
            override fun onEvent(eventType: Int, params: Bundle?) {}
        })
    }

    fun start() {
        problem = null
        partial = ""
        isListening = true
        recognizer.startListening(
            Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH)
                .putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL, RecognizerIntent.LANGUAGE_MODEL_FREE_FORM)
                .putExtra(RecognizerIntent.EXTRA_LANGUAGE, language.localeTag)
                .putExtra(RecognizerIntent.EXTRA_MAX_RESULTS, MAXIMUM_GUESSES)
                .putExtra(RecognizerIntent.EXTRA_PARTIAL_RESULTS, true),
        )
    }

    /** Stops listening early. What was heard so far still arrives as an answer. */
    fun stop() = recognizer.stopListening()

    fun shutdown() = recognizer.destroy()

    private fun describe(error: Int): String = when (error) {
        SpeechRecognizer.ERROR_NO_MATCH, SpeechRecognizer.ERROR_SPEECH_TIMEOUT -> NOT_HEARD
        SpeechRecognizer.ERROR_INSUFFICIENT_PERMISSIONS ->
            "CatLang needs microphone access for speaking. Allow it in the app's settings."
        SpeechRecognizer.ERROR_LANGUAGE_NOT_SUPPORTED, SpeechRecognizer.ERROR_LANGUAGE_UNAVAILABLE ->
            "Your device's speech recognition doesn't support ${language.displayName} yet. " +
                "You may be able to add it in its language settings."
        SpeechRecognizer.ERROR_NETWORK, SpeechRecognizer.ERROR_NETWORK_TIMEOUT,
        SpeechRecognizer.ERROR_SERVER, SpeechRecognizer.ERROR_SERVER_DISCONNECTED ->
            "Couldn't reach the speech recognition service. Check your connection and try again."
        SpeechRecognizer.ERROR_RECOGNIZER_BUSY -> "Speech recognition is busy. Try again in a moment."
        else -> "Speech recognition stopped unexpectedly. Try again."
    }

    private companion object {
        const val NOT_HEARD = "Didn't catch that. Tap the microphone and try again."
    }
}
