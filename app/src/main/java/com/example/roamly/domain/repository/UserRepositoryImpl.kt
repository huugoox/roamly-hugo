package com.example.roamly.domain.repository

import com.example.roamly.domain.models.User
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class UserRepositoryImpl @Inject constructor() : UserRepository {

    private val users = mutableListOf<User>()

    override fun getUserById(id: String): User? {
        return users.find { it.id == id }
    }

    override fun getAllUsers(): List<User> {
        return users
    }

    override fun updateUser(user: User): Boolean {
        val index = users.indexOfFirst { it.id == user.id }
        return if (index != -1) {
            users[index] = user
            true
        } else {
            false
        }
    }

    override fun deleteUser(id: String): Boolean {
        return users.removeIf { it.id == id }
    }

}