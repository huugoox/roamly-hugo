package com.example.navigation.models

data class Authentication(
    val email: String,
    val password: String
) {
    // @TODO: Implement login function
    fun login(email: String, password: String) {}

    // @TODO: Implement logout function
    fun logout() {}

    // @TODO: Implement password reset function
    fun resetPassword(email: String) {}
}
