package com.example.navigation.models

data class User(
    val id: String,
    val name: String,
    val email: String,
    val phoneNumber: String,
    val profilePictureUrl: String,
    val bio: String,
    val followers: Int,
    val following: Int,
    val totalLikes: Int,
    val uploadedRoutes: Int
) {
    // @TODO: Implement user registration
    fun register() {}

    // @TODO: Implement user login
    fun login(email: String, password: String) {}

    // @TODO: Implement user logout
    fun logout() {}
}
