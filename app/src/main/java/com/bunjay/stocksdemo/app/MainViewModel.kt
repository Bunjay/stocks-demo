package com.bunjay.stocksdemo.app

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.bunjay.stocksdemo.app.domain.ObserveThemeModeUseCase
import com.bunjay.stocksdemo.app.domain.SetThemeModeUseCase
import com.bunjay.stocksdemo.app.ui.theme.ThemeMode
import com.bunjay.stocksdemo.app.ui.theme.next
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    observeThemeModeUseCase: ObserveThemeModeUseCase,
    private val setThemeModeUseCase: SetThemeModeUseCase,
) : ViewModel() {
    val themeMode: StateFlow<ThemeMode> =
        observeThemeModeUseCase().stateIn(viewModelScope, SharingStarted.Eagerly, ThemeMode.SYSTEM)

    fun toggleTheme() {
        viewModelScope.launch { setThemeModeUseCase(themeMode.value.next()) }
    }
}