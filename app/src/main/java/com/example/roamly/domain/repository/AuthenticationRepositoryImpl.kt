package com.example.roamly.domain.repository

import com.example.roamly.domain.models.User
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class AuthenticationRepositoryImpl @Inject constructor() : AuthenticationRepository {

    private val users = mutableMapOf<String, Pair<User, String>>()
    private var loggedInUser: User? = null

    override fun login(email: String, password: String): User? {
        val userData = users[email]
        return if (userData!= null && userData.second == password) {
            loggedInUser = userData.first
            loggedInUser
        } else {
            null
        }
    }

    override fun logout() {
        loggedInUser = null
    }

    override fun resetPassword(email: String): Boolean {
        return if (users.containsKey(email)) {
            println("Se ha enviado un correo de recuperación a $email")
            true
        } else {
            false
        }
    }

    override fun register(user: User, password: String): Boolean {
        return if (users.containsKey(user.email)) {
            false
        } else {
            users[user.email] = Pair(user, password)
            true
        }
    }

    override fun getCurrentUser(): User? {
        return loggedInUser
    }

}