package com.example.navigation.domain.repository

import com.example.navigation.domain.models.User

interface AuthenticationRepository {

    fun login(email: String, password: String): User?
    fun logout()
    fun resetPassword(email: String): Boolean
    fun register(user: User, password: String): Boolean
    fun getCurrentUser(): User?
}