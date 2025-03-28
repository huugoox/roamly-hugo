package com.example.roamly.domain.models

data class Preferences(
    val id: Int,
    val notificationsEnabled: Boolean,
    val preferredLanguage: String,
    val theme: String
)
