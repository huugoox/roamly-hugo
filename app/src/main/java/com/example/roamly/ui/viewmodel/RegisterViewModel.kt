package com.example.roamly.ui.viewmodel

import android.content.Context
import android.util.Log
import android.widget.Toast
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.authentication.ui.viewmodels.AuthViewModel
import com.example.roamly.R
import com.example.roamly.data.local.dao.UsersDao
import com.example.roamly.data.local.entity.UserEntity
import com.example.roamly.domain.repository.UserRepository
import com.example.roamly.utils.FormValidationUtils
import com.google.firebase.auth.FirebaseAuth
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.launch
import java.text.SimpleDateFormat
import java.util.*
import javax.inject.Inject

@HiltViewModel
class RegisterViewModel @Inject constructor(
    @ApplicationContext private val context: Context,
    private val userRepository: UserRepository,
    private val userDao: UsersDao // Inyectamos el DAO
) : ViewModel() {

    private val auth: FirebaseAuth = FirebaseAuth.getInstance()

    // ====================== Campos del formulario ======================
    var fullName by mutableStateOf("")
        private set

    var email by mutableStateOf("")
        private set

    var username by mutableStateOf("")
        private set

    var password by mutableStateOf("")
        private set

    var confirmPassword by mutableStateOf("")
        private set

    var birthdate by mutableStateOf("")
        private set

    var address by mutableStateOf("")
        private set

    var country by mutableStateOf("")
        private set

    var phoneNumber by mutableStateOf("")
        private set

    var acceptEmails by mutableStateOf(false)
        private set

    // ========================== Mensajes de error ==========================
    var fullNameError by mutableStateOf<String?>(null)
        private set

    var emailError by mutableStateOf<String?>(null)
        private set

    var usernameError by mutableStateOf<String?>(null)

    var passwordError by mutableStateOf<String?>(null)
        private set

    var confirmPasswordError by mutableStateOf<String?>(null)
        private set

    var birthdateError by mutableStateOf<String?>(null)
        private set

    var addressError by mutableStateOf<String?>(null)
        private set

    var countryError by mutableStateOf<String?>(null)
        private set

    var phoneNumberError by mutableStateOf<String?>(null)
        private set

    // ========================== Setters de cada campo ==========================
    fun onFullNameChanged(newValue: String) {
        fullName = newValue
    }

    fun onEmailChanged(newValue: String) {
        email = newValue
    }

    fun onUsernameChanged(newValue: String) {
        username = newValue
    }

    fun onPasswordChanged(newValue: String) {
        password = newValue
    }

    fun onConfirmPasswordChanged(newValue: String) {
        confirmPassword = newValue
    }

    fun onBirthdateChanged(newValue: String) {
        birthdate = newValue
    }

    fun onAddressChanged(newValue: String) {
        address = newValue
    }

    fun onCountryChanged(newValue: String) {
        country = newValue
    }

    fun onPhoneNumberChanged(newValue: String) {
        phoneNumber = newValue
    }

    fun onAcceptEmailsChanged(newValue: Boolean) {
        acceptEmails = newValue
    }

    // ========================== Acción principal: Registrar ==========================
    fun onRegisterClicked(): Boolean {

        val allValid = validateAllFields()

        if (allValid) {
            val birthdateTimestamp = convertToTimestamp(birthdate)

            // Creamos el objeto UserEntity
            val userEntity = UserEntity(
                fullName = fullName,
                email = email,
                username = username,
                password = password,
                birthdate = birthdateTimestamp,
                address = address,
                country = country,
                phoneNumber = phoneNumber,
                acceptEmails = acceptEmails,
                uploadedRoutes = 0, // Valor predeterminado
                followers = 0, // Valor predeterminado
                following = 0, // Valor predeterminado
                totalLikes = 0 // Valor predeterminado
            )

            registerUserInFirebase(userEntity)


            Toast.makeText(context, "Registro exitoso", Toast.LENGTH_SHORT).show()
            return true
        } else {
            return false
        }
    }

    private fun registerUserInFirebase(userEntity: UserEntity) {
        auth.createUserWithEmailAndPassword(userEntity.email, userEntity.password)
            .addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    // Después de registrar al usuario, enviar correo de verificación
                    AuthViewModel().sendEmailVerification()

                    // Insertar el usuario en la base de datos local
                    insertUser(userEntity)

                    Toast.makeText(context, "Verifica tu correo electrónico", Toast.LENGTH_SHORT).show()
                } else {
                    Log.e("RegisterViewModel", "Error al registrar usuario en Firebase: ${task.exception?.message}")
                }
            }
    }

    // ========================== Método para insertar el usuario en Room ==========================
    private fun insertUser(userEntity: UserEntity) {
        viewModelScope.launch {
            try {
                userDao.addUser(userEntity)
                Log.d("RegisterViewModel", "Usuario insertado en la base de datos con éxito")
            } catch (e: Exception) {
                Log.e("RegisterViewModel", "Error al insertar usuario: ${e.message}")
            }
        }
    }

    fun checkUsernameExists(username: String, onResult: (Boolean) -> Unit) {
        viewModelScope.launch {
            val existingUsername = userRepository.getUserByUsername(username)
            val exists = existingUsername != null
            onResult(exists)
        }
    }

    // ========================== Convertir la fecha de nacimiento en timestamp ==========================
    private fun convertToTimestamp(birthdate: String): Long {
        return try {
            val sdf = SimpleDateFormat("dd/MM/yyyy", Locale.getDefault())
            val date: Date = sdf.parse(birthdate) ?: Date()
            date.time
        } catch (e: Exception) {
            0L // Si hay un error al convertir, devolvemos 0
        }
    }

    // ========================== Validación de todos los campos ==========================
    fun validateAllFields(): Boolean {
        val isFullNameValid = fullName.isNotBlank()
        val isEmailValid = FormValidationUtils.validateUserEmail(email)
        val isUsernameValid = FormValidationUtils.validateUsername(username)
        val isPasswordValid = FormValidationUtils.validatePassword(password)
        val isConfirmPasswordValid = FormValidationUtils.validateConfirmationPassword(password, confirmPassword)
        val isBirthdateValid = birthdate.isNotBlank()
        val isAddressValid = address.isNotBlank()
        val isCountryValid = country.isNotBlank()
        val isPhoneNumberValid = phoneNumber.isNotBlank()


        // Asignamos errores si no pasa
        fullNameError = if (!isFullNameValid) context.getString(R.string.error_required_field) else null
        emailError = if (!isEmailValid) context.getString(R.string.error_invalid_email) else null
        usernameError = if (!isUsernameValid) context.getString(R.string.error_required_field) else null
        passwordError = if (!isPasswordValid) context.getString(R.string.error_password_too_short) else null
        confirmPasswordError = if (!isConfirmPasswordValid) context.getString(R.string.error_password_mismatch) else null
        birthdateError = if (!isBirthdateValid) context.getString(R.string.error_required_field) else null
        addressError = if (!isAddressValid) context.getString(R.string.error_required_field) else null
        countryError = if (!isCountryValid) context.getString(R.string.error_required_field) else null
        phoneNumberError = if (!isPhoneNumberValid) context.getString(R.string.error_mobile_digits) else null

        // Devuelve true solo si todo es válido
        return isFullNameValid && isEmailValid && isUsernameValid && isPasswordValid &&
                isConfirmPasswordValid && isBirthdateValid && isAddressValid &&
                isCountryValid && isPhoneNumberValid
    }
}
