package com.example.roamly.data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.Date

@Entity(tableName = "users")
data class UserEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
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
