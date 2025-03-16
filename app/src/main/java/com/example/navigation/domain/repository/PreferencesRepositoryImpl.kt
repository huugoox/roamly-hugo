package com.example.navigation.domain.repository

import com.example.navigation.domain.models.Preferences
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class PreferencesRepositoryImpl @Inject constructor() : PreferencesRepository {

    private val preferencesList = mutableListOf<Preferences>()

    override fun addPreferences(preferences: Preferences): Boolean {
        return if (preferencesList.any() { it.id == preferences.id }) {
            false
        } else {
            preferencesList.add(preferences)
            true
        }
    }

    override fun getPreferences(id: String): Preferences? {
        return preferencesList.find { it.id == id }
    }

    override fun updatePreferences(
        id: String,
        notificationsEnabled: Boolean?,
        preferredLanguage: String?,
        theme: String?
    ): Boolean {
        val index = preferencesList.indexOfFirst { it.id == id }

        return if (index != -1) {
            val currentPreferences = preferencesList[index]
            preferencesList[index] = currentPreferences.copy(
                notificationsEnabled = notificationsEnabled?: currentPreferences.notificationsEnabled,
                preferredLanguage = preferredLanguage ?: currentPreferences.preferredLanguage,
                theme = theme ?: currentPreferences.theme
            )
            true
        } else{
            false
        }
    }

    override fun deletePreferences(id: String): Boolean {
        return preferencesList.removeIf { it.id  == id}
    }
}