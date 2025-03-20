package com.example.roamly.domain.repository

import com.example.roamly.domain.models.User

interface AuthenticationRepository {

    fun login(email: String, password: String): User?
    fun logout()
    fun resetPassword(email: String): Boolean
    fun register(user: User, password: String): Boolean
    fun getCurrentUser(): User?
}