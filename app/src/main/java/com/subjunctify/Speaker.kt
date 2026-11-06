package com.subjunctify

import android.content.Context
import android.speech.tts.TextToSpeech
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import java.util.Locale

enum class VoiceStatus { LOADING, READY, UNAVAILABLE }

/** Speaks [language] through the device's text-to-speech engine. */
class Speaker(context: Context, private val language: Language) {
    var status by mutableStateOf(VoiceStatus.LOADING)
        private set

    private val tts: TextToSpeech = TextToSpeech(context.applicationContext) { initStatus ->
        status = if (initStatus == TextToSpeech.SUCCESS && selectVoice()) {
            VoiceStatus.READY
        } else {
            VoiceStatus.UNAVAILABLE
        }
    }

    private fun selectVoice(): Boolean {
        val result = tts.setLanguage(Locale.forLanguageTag(language.localeTag))
        return result == TextToSpeech.LANG_COUNTRY_AVAILABLE ||
            result == TextToSpeech.LANG_COUNTRY_VAR_AVAILABLE ||
            (result == TextToSpeech.LANG_AVAILABLE && !language.requiresRegionalVoice)
    }

    fun speak(text: String, slowly: Boolean = false) {
        tts.setSpeechRate(if (slowly) 0.6f else 1f)
        tts.speak(text, TextToSpeech.QUEUE_FLUSH, null, "exercise")
    }

    fun shutdown() = tts.shutdown()
}
