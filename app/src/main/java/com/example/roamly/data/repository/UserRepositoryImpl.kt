package com.example.roamly.data.repository

import android.util.Log
import com.example.roamly.data.local.dao.UsersDao
import com.example.roamly.data.local.entity.UserEntity
import com.example.roamly.data.local.mapper.toDomain
import com.example.roamly.data.local.mapper.toEntity
import com.example.roamly.domain.models.User
import com.example.roamly.domain.repository.UserRepository
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class UserRepositoryImpl @Inject constructor(
    private val usersDao: UsersDao // Inyectamos el UsersDao para interactuar con la base de datos
) : UserRepository {

    private val TAG = "UserRepositoryImpl" // Definir un TAG para los logs

    override suspend fun getUserById(id: Int): User? {
        Log.d(TAG, "Buscando usuario con ID: $id")
        val userEntity = usersDao.getUserById(id) // Obtenemos el usuario de la base de datos
        return userEntity?.toDomain() // Convertimos a modelo de dominio
    }

    override suspend fun getUserByUsername(username: String): User? {
        Log.d(TAG, "Buscando usuario con username: $username")
        val userEntity = usersDao.getUserByUsername(username) // Obtenemos el usuario de la base de datos
        return userEntity?.toDomain() // Convertimos a modelo de dominio
    }


    override suspend fun getAllUsers(): List<User> {
        Log.d(TAG, "Obteniendo todos los usuarios")
        return usersDao.getUsers().map { it.toDomain() } // Obtenemos todos los usuarios y los convertimos
    }

    override suspend fun updateUser(user: User): Boolean {
        Log.d(TAG, "Intentando actualizar el usuario con ID: ${user.id}")
        val userEntity = user.toEntity() // Convertimos el modelo de dominio a entidad de base de datos
        val rowsUpdated = usersDao.updateUser(userEntity) // Actualizamos en la base de datos
        return rowsUpdated > 0 // Si se actualizó alguna fila, devolvemos true
    }

    override suspend fun deleteUser(id: Int): Boolean {
        Log.d(TAG, "Intentando eliminar el usuario con ID: $id")
        val userEntity = usersDao.getUserById(id) // Obtenemos el usuario a eliminar
        return if (userEntity != null) {
            usersDao.deleteUser(userEntity) // Si existe, lo eliminamos
            Log.d(TAG, "Usuario con ID: $id eliminado correctamente")
            true
        } else {
            Log.d(TAG, "Usuario con ID: $id no encontrado para eliminar")
            false
        }
    }
}
