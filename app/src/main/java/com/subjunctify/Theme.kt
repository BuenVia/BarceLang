package com.subjunctify

import android.content.Context
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.graphics.Color

// Every role the app draws with is set here, because Material's defaults include brown-tinted reds
// and purple-tinted surfaces. Tertiary is the green that marks success: right answers, started lessons.
private val LightColors = lightColorScheme(
    primary = Color(0xFF2F5DA8),
    onPrimary = Color.White,
    primaryContainer = Color(0xFFD7E3FF),
    onPrimaryContainer = Color(0xFF001B3F),
    secondary = Color(0xFF555F71),
    onSecondary = Color.White,
    secondaryContainer = Color(0xFFD9E3F8),
    onSecondaryContainer = Color(0xFF121C2B),
    tertiary = Color(0xFF1B6B51),
    onTertiary = Color.White,
    tertiaryContainer = Color(0xFFB6F0D3),
    onTertiaryContainer = Color(0xFF002116),
    error = Color(0xFFB3173F),
    onError = Color.White,
    errorContainer = Color(0xFFFFD9E0),
    onErrorContainer = Color(0xFF40000F),
    background = Color(0xFFF7F9FF),
    onBackground = Color(0xFF191C22),
    surface = Color(0xFFF7F9FF),
    onSurface = Color(0xFF191C22),
    surfaceVariant = Color(0xFFE0E2EC),
    onSurfaceVariant = Color(0xFF43474E),
    surfaceContainerLowest = Color.White,
    surfaceContainerLow = Color(0xFFF1F4FA),
    surfaceContainer = Color(0xFFEBEEF5),
    surfaceContainerHigh = Color(0xFFE6E9F1),
    surfaceContainerHighest = Color(0xFFE1E5EE),
    outline = Color(0xFF73777F),
    outlineVariant = Color(0xFFC3C6CF),
)

private val DarkColors = darkColorScheme(
    primary = Color(0xFFA9C7FF),
    onPrimary = Color(0xFF003062),
    primaryContainer = Color(0xFF2A4A82),
    onPrimaryContainer = Color(0xFFD7E3FF),
    secondary = Color(0xFFBDC7DC),
    onSecondary = Color(0xFF273141),
    secondaryContainer = Color(0xFF3E4758),
    onSecondaryContainer = Color(0xFFD9E3F8),
    tertiary = Color(0xFF8FD6B5),
    onTertiary = Color(0xFF00382A),
    tertiaryContainer = Color(0xFF14513D),
    onTertiaryContainer = Color(0xFFB6F0D3),
    error = Color(0xFFFFB2BF),
    onError = Color(0xFF66001F),
    errorContainer = Color(0xFF6E1F3F),
    onErrorContainer = Color(0xFFFFD9E0),
    background = Color(0xFF12161F),
    onBackground = Color(0xFFE2E6EF),
    surface = Color(0xFF12161F),
    onSurface = Color(0xFFE2E6EF),
    surfaceVariant = Color(0xFF43474E),
    onSurfaceVariant = Color(0xFFC3C6CF),
    surfaceContainerLowest = Color(0xFF0C0F16),
    surfaceContainerLow = Color(0xFF1A1E27),
    surfaceContainer = Color(0xFF1E222B),
    surfaceContainerHigh = Color(0xFF242833),
    surfaceContainerHighest = Color(0xFF2A2F3A),
    outline = Color(0xFF8D9199),
    outlineVariant = Color(0xFF43474E),
)

/** The theme in use, and how the toggle in the top bar flips it. */
class ThemeState(val isDark: Boolean, val toggle: () -> Unit)

val LocalThemeState = staticCompositionLocalOf<ThemeState> { error("No theme provided") }

@Composable
fun AppTheme(state: ThemeState, content: @Composable () -> Unit) {
    CompositionLocalProvider(LocalThemeState provides state) {
        MaterialTheme(colorScheme = if (state.isDark) DarkColors else LightColors, content = content)
    }
}

/** Remembers the toggle's setting. Null until it's first used, when the app follows the device. */
class ThemePreference(context: Context) {
    private val preferences = context.getSharedPreferences("settings", Context.MODE_PRIVATE)

    fun load(): Boolean? =
        if (preferences.contains(KEY_DARK)) preferences.getBoolean(KEY_DARK, false) else null

    fun save(dark: Boolean) {
        preferences.edit().putBoolean(KEY_DARK, dark).apply()
    }

    private companion object {
        const val KEY_DARK = "dark_theme"
    }
}
