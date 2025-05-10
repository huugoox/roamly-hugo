package com.example.roamly.domain.models

import java.util.Date

data class User(
    val id: Int,
    val fullName: String,
    val email: String,
    val username: String,
    val password: String,
    val birthdate: Long,
    val address: String,
    val country: String,
    val phoneNumber: String,
    val acceptEmails: Boolean,
    val uploadedRoutes: Int,
    val followers: Int,
    val following: Int,
    val totalLikes: Int
)


