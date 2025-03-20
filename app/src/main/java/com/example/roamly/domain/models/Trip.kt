package com.example.roamly.domain.models

import java.util.Date

data class Trip(
    val id: String,
    val destination: String,
    val startDate: Date,
    val endDate: Date,
    val budget: Double,
    val notes: String = "",
    val isFavorite: Boolean = false,
    val coverImageUrl: String? = null
)
