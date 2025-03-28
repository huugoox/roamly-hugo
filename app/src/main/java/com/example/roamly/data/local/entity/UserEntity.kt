package com.example.roamly.data.local.entity

import androidx.room.Entity

@Entity(tableName = "users", primaryKeys = ["id"])
data class UserEntity(
    val id: Int,
    val name: String,
    val email: String,
    val phoneNumber: String,
    val profilePictureUrl: String? = null,
    val bio: String = "",
    val followers: Int = 0,
    val following: Int = 0,
    val totalLikes: Int = 0,
    val uploadedRoutes: Int = 0
)