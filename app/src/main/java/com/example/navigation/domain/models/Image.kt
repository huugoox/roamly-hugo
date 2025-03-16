package com.example.navigation.domain.models

import com.google.protobuf.Timestamp
import java.util.Date

data class Image(
    val id: String,
    val url: String,
    val description: String,
    val timestamp: Date = Date()
)
