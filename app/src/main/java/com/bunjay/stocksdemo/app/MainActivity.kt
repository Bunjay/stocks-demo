package com.bunjay.stocksdemo.app

import android.graphics.Color
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.SystemBarStyle
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.getValue
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.bunjay.stocksdemo.app.ui.theme.StocksDemoTheme
import com.bunjay.stocksdemo.app.ui.theme.isDark
import com.bunjay.stocksdemo.feature.stock.ui.StockListScreen
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            val viewmodel: MainViewModel = hiltViewModel()
            val themeMode by viewmodel.themeMode.collectAsStateWithLifecycle()
            val darkTheme = themeMode.isDark(systemDark = isSystemInDarkTheme())

            DisposableEffect(darkTheme) {
                enableEdgeToEdge(
                    statusBarStyle = SystemBarStyle.auto(
                        Color.TRANSPARENT,
                        Color.TRANSPARENT
                    ) { darkTheme },
                    navigationBarStyle = SystemBarStyle.auto(LightScrim, DarkScrim) { darkTheme },
                )
                onDispose {}
            }
            StocksDemoTheme(darkTheme = darkTheme) {
                StockListScreen(themeMode = themeMode, onToggleTheme = viewmodel::toggleTheme)
            }
        }
    }
}

private val LightScrim = Color.argb(0xe6, 0xFF, 0xFF, 0xFF)
private val DarkScrim = Color.argb(0x80, 0x1b, 0x1b, 0x1b)