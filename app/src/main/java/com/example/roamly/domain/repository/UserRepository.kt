package com.example.roamly.domain.repository

import com.example.roamly.domain.models.User

interface UserRepository {
    fun getUserById(id: Int): User?
    fun getAllUsers(): List<User>
    fun updateUser(user: User): Boolean
    fun deleteUser(id: Int): Boolean
}