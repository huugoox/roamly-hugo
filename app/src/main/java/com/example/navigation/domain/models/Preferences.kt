package com.example.navigation.domain.models

data class Preferences(
    val id: String,
    val notificationsEnabled: Boolean,
    val preferredLanguage: String,
    val theme: String
) {
    // @TODO: Implement update preferences function
    fun updatePreferences() {}
    fun getPreferences() {}

}
