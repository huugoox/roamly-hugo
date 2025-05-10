package com.example.roamly.domain.repository

import com.example.roamly.domain.models.User

interface UserRepository {
    suspend fun getUserById(id: Int): User?
    suspend fun getUserByUsername(username: String): User?
    suspend fun getAllUsers(): List<User>
    suspend fun updateUser(user: User): Boolean
    suspend fun deleteUser(id: Int): Boolean
}