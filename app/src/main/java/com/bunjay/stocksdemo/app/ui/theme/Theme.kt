package com.bunjay.stocksdemo.app.ui.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.staticCompositionLocalOf
import com.materialkolor.material3.DynamicMaterialTheme


val LocalAppColors = staticCompositionLocalOf {
    LightAppColors
}

@Composable
fun StocksDemoTheme(
    darkTheme: Boolean = isSystemInDarkTheme(), content: @Composable () -> Unit
) {
    val appColors = if (darkTheme) DarkAppColors else LightAppColors
    CompositionLocalProvider(LocalAppColors provides appColors) {
        DynamicMaterialTheme(
            seedColor = ThemeSeedColor,
            isDark = darkTheme,
            animate = true,
            typography = Typography,
            content = content
        )
    }

}

enum class ThemeMode { SYSTEM, LIGHT, DARK }

fun ThemeMode.next(): ThemeMode = when (this) {
    ThemeMode.SYSTEM -> ThemeMode.LIGHT
    ThemeMode.LIGHT -> ThemeMode.DARK
    ThemeMode.DARK -> ThemeMode.SYSTEM
}

fun ThemeMode.isDark(systemDark: Boolean): Boolean = when (this) {
    ThemeMode.SYSTEM -> systemDark
    ThemeMode.LIGHT -> false
    ThemeMode.DARK -> true
}