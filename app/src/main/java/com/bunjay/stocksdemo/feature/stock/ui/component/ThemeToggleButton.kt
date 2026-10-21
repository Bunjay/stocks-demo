package com.bunjay.stocksdemo.feature.stock.ui.component

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.BrightnessAuto
import androidx.compose.material.icons.filled.DarkMode
import androidx.compose.material.icons.filled.LightMode
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.runtime.Composable
import com.bunjay.stocksdemo.app.ui.theme.ThemeMode

@Composable
fun ThemeToggleButton(themeMode: ThemeMode, onClick: () -> Unit) {
    val icon = when (themeMode) {
        ThemeMode.SYSTEM -> Icons.Filled.BrightnessAuto
        ThemeMode.LIGHT -> Icons.Filled.LightMode
        ThemeMode.DARK -> Icons.Filled.DarkMode
    }
    IconButton(onClick = onClick) {
        Icon(icon, contentDescription = null)
    }
}