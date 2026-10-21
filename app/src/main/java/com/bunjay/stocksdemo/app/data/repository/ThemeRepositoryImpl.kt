package com.bunjay.stocksdemo.app.data.repository

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.emptyPreferences
import androidx.datastore.preferences.core.stringPreferencesKey
import com.bunjay.stocksdemo.app.domain.ThemeRepository
import com.bunjay.stocksdemo.app.ui.theme.ThemeMode
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.map
import java.io.IOException
import javax.inject.Inject

private val THEME_MODE_KEY = stringPreferencesKey("theme_mode")

class ThemeRepositoryImpl @Inject constructor(
    private val dataStore: DataStore<Preferences>,
) : ThemeRepository {

    override val themeMode: Flow<ThemeMode> = dataStore.data
        .catch { if (it is IOException) emit(emptyPreferences()) else throw it }
        .map { prefs -> ThemeMode.entries.firstOrNull { it.name == prefs[THEME_MODE_KEY] } ?: ThemeMode.SYSTEM }
        .distinctUntilChanged()

    override suspend fun setThemeMode(mode: ThemeMode) {
        dataStore.edit { it[THEME_MODE_KEY] = mode.name }
    }
}