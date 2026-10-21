package com.bunjay.stocksdemo.app.domain

import com.bunjay.stocksdemo.app.ui.theme.ThemeMode
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class ObserveThemeModeUseCase @Inject constructor(
    private val repository: ThemeRepository,
) {
    operator fun invoke(): Flow<ThemeMode> = repository.themeMode
}