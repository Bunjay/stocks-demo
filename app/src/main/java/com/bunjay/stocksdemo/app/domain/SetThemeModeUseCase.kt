package com.bunjay.stocksdemo.app.domain

import com.bunjay.stocksdemo.app.ui.theme.ThemeMode
import javax.inject.Inject

class SetThemeModeUseCase @Inject constructor(
    private val repository: ThemeRepository,
) {
    suspend operator fun invoke(mode: ThemeMode) = repository.setThemeMode(mode)
}