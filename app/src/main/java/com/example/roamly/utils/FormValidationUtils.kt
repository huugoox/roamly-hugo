package com.example.roamly.utils
import android.util.Patterns

object FormValidationUtils {

    fun validateUserEmail(email: String): Boolean {
        return isEmailValid(email)
    }

    fun validatePassword(password: String): Boolean {
        return password.isNotEmpty() && password.length >= 6
    }

    fun validateConfirmationPassword(
        password: String,
        confirmationPassword: String
    ): Boolean {
        return confirmationPassword.isNotEmpty() && confirmationPassword == password
    }

    fun validateStoreName(storeName: String): Boolean {
        return storeName.isNotEmpty()
    }

    fun validateStoreLocation(storeLoc: String): Boolean {
        return storeLoc.isNotEmpty()
    }

    fun validateMobile(mobile: String): Boolean {
        return mobile.isNotEmpty() && mobile.length == 10
    }

    fun validatePin(pinCode: String): Boolean {
        return pinCode.isNotEmpty() && pinCode.length == 6
    }

    fun validateUsername(username: String): Boolean {
        return username.isNotEmpty() && username.length in 3..20  // Validación básica: no vacío y longitud entre 3 y 20 caracteres
    }

    private fun isEmailValid(email: String): Boolean {
        return Patterns.EMAIL_ADDRESS.matcher(email).matches()
    }


}
