package com.example.navigation.domain.repository

import com.example.navigation.domain.models.User

interface UserRepository {
    fun getUserById(id: String): User?
    fun getAllUsers(): List<User>
    fun updateUser(user: User): Boolean
    fun deleteUser(id: String): Boolean
}