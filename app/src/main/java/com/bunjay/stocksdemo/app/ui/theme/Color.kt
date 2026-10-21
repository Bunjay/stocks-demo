package com.bunjay.stocksdemo.app.ui.theme

import androidx.compose.runtime.Immutable
import androidx.compose.ui.graphics.Color


val ThemeSeedColor = Color(0xFF45CE99)

@Immutable
data class AppColors(
    val stockUp: Color,
    val stockDown: Color
)

val LightAppColors = AppColors(
    stockUp = Color(0xFFD32F2F),
    stockDown = Color(0xFF2E7D32)
)

val DarkAppColors = AppColors(
    stockUp = Color(0xFFFF8A80),
    stockDown = Color(0xFF81C784)
)