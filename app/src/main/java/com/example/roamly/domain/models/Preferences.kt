package com.example.roamly.domain.models

data class Preferences(
    val id: String,
    val notificationsEnabled: Boolean,
    val preferredLanguage: String,
    val theme: String
)
