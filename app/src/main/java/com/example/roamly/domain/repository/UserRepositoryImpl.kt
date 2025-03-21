package com.example.roamly.domain.repository

import android.util.Log
import javax.inject.Inject
import javax.inject.Singleton
import com.example.roamly.domain.models.User

@Singleton
class UserRepositoryImpl @Inject constructor() : UserRepository {

    private val users = mutableListOf<User>()
    private val TAG = "UserRepositoryImpl" // Definir un TAG para los logs

    override fun getUserById(id: String): User? {
        Log.d(TAG, "Buscando usuario con ID: $id")
        return users.find { it.id == id }
    }

    override fun getAllUsers(): List<User> {
        Log.d(TAG, "Obteniendo todos los usuarios")
        return users
    }

    override fun updateUser(user: User): Boolean {
        Log.d(TAG, "Intentando actualizar el usuario con ID: ${user.id}")

        val index = users.indexOfFirst { it.id == user.id }
        return if (index != -1) {
            users[index] = user
            Log.d(TAG, "Usuario con ID: ${user.id} actualizado correctamente")
            true
        } else {
            Log.d(TAG, "Usuario con ID: ${user.id} no encontrado")
            false
        }
    }

    override fun deleteUser(id: String): Boolean {
        Log.d(TAG, "Intentando eliminar el usuario con ID: $id")
        val result = users.removeIf { it.id == id }
        if (result) {
            Log.d(TAG, "Usuario con ID: $id eliminado correctamente")
        } else {
            Log.d(TAG, "Usuario con ID: $id no encontrado para eliminar")
        }
        return result
    }
}
