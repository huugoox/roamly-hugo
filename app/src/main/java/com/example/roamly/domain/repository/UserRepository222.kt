package com.example.roamly.domain.repository

import com.example.roamly.domain.models.User
import java.util.Date

object UserRepository222 {
    val currentUser = User(
        id = 123,
        fullName = "John Doe",  // Adaptado de name a fullName
        email = "johndoe@example.com",
        username = "johndoe123",  // Adaptado de profile picture y otros a username
        password = "password123", // Esto es solo un ejemplo, en la vida real nunca se debe almacenar así
        birthdate = Date().time,  // Usamos Date().time para obtener el timestamp actual en milisegundos
        address = "123 Adventure St.",
        country = "Explorandia",  // Suponiendo un país ficticio
        phoneNumber = "+123456789",
        acceptEmails = true,
        uploadedRoutes = 6,
        followers = 100,
        following = 50,
        totalLikes = 200
    )
}
