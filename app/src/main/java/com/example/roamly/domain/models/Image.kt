package com.example.roamly.domain.models

import java.util.Date

data class Image(
    val id: Int,
    val url: String,
    val description: String,
    val timestamp: Date = Date()
)
