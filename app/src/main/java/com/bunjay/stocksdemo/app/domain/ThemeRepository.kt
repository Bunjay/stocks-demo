package com.bunjay.stocksdemo.app.domain

import com.bunjay.stocksdemo.app.ui.theme.ThemeMode
import kotlinx.coroutines.flow.Flow

interface ThemeRepository {
    val themeMode: Flow<ThemeMode>
    suspend fun setThemeMode(mode: ThemeMode)
}