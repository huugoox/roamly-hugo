package com.example.roamly.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import androidx.room.Delete
import com.example.roamly.data.local.entity.UserEntity

@Dao
interface UsersDao {

    // Obtener todos los usuarios
    @Query("SELECT * FROM users")
    suspend fun getUsers(): List<UserEntity>

    // Obtener un usuario por su ID
    @Query("SELECT * FROM users WHERE id = :userId LIMIT 1")
    suspend fun getUserById(userId: Int): UserEntity?

    // Obtener un usuario por su login
    @Query("SELECT * FROM users WHERE email = :email LIMIT 1")
    suspend fun getUserByLogin(email: String): UserEntity?

    // Verificar si un nombre de usuario ya existe
    @Query("SELECT * FROM users WHERE username = :username LIMIT 1")
    suspend fun getUserByUsername(username: String): UserEntity?

    // Insertar un nuevo usuario
    @Insert
    suspend fun addUser(user: UserEntity): Long

    // Actualizar un usuario existente
    @Update
    suspend fun updateUser(user: UserEntity): Int

    // Eliminar un usuario por su ID
    @Delete
    suspend fun deleteUser(user: UserEntity)

    // Eliminar un usuario por su login
    @Query("DELETE FROM users WHERE email = :email")
    suspend fun deleteUserByLogin(email: String)
}
