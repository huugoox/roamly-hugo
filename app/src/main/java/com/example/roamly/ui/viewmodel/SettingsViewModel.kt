package com.example.roamly.ui.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.example.roamly.data.SharedPrefsManager
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class SettingsViewModel @Inject constructor(
    private val sharedPrefsManager: SharedPrefsManager
) : ViewModel() {
    var language by mutableStateOf(sharedPrefsManager.userLanguage ?: "es")
        private set

    var isDarkTheme by mutableStateOf(sharedPrefsManager.darkTheme)
        private set

    fun updateLanguage(newLanguage: String) {
        sharedPrefsManager.userLanguage = newLanguage
        language = newLanguage
    }

    fun updateDarkTheme(isDark: Boolean) {
        sharedPrefsManager.darkTheme = isDark
        isDarkTheme = isDark
    }
}