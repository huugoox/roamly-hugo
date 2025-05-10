package com.example.roamly.ui.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.authentication.ui.viewmodels.AuthState
import com.google.firebase.auth.FirebaseAuth
import kotlinx.coroutines.launch
import kotlinx.coroutines.tasks.await
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class LoginViewModel : ViewModel() {

    private val TAG = "LoginViewModel"

    private val auth: FirebaseAuth = FirebaseAuth.getInstance()

    private val _authState = MutableLiveData<AuthState>()
    val authState: LiveData<AuthState> = _authState

    var email = MutableStateFlow("hugoferod@gmail.com")
        private set

    var password = MutableStateFlow("123456")
        private set

    var showAlert = MutableStateFlow(false)
        private set

    var showRecoverDialog = MutableStateFlow(false)
        private set

    var showRecoverDialogRes = MutableStateFlow(false)
        private set

    var recoveryEmail = MutableStateFlow("")
        private set

    var recoveryMessage = MutableStateFlow("")
        private set

    fun onEmailChange(newValue: String) {
        email.value = newValue
    }

    fun onPasswordChange(newValue: String) {
        password.value = newValue
    }

    fun onRecoveryEmailChange(newValue: String) {
        recoveryEmail.value = newValue
    }

    fun setShowAlert(value: Boolean) {
        showAlert.value = value
    }

    fun setShowRecoverDialog(value: Boolean) {
        showRecoverDialog.value = value
    }

    fun setShowRecoverDialogRes(value: Boolean) {
        showRecoverDialogRes.value = value
    }

    fun login(email: String, password: String) {
        if (email.isEmpty() || password.isEmpty()) {
            _authState.value = AuthState.Error("Email or password can't be empty")
            return
        }

        _authState.value = AuthState.Loading
        auth.signInWithEmailAndPassword(email, password)
            .addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    val user = auth.currentUser
                    user?.reload()?.addOnCompleteListener { reloadTask ->
                        if (reloadTask.isSuccessful) {
                            if (user.isEmailVerified) {
                                _authState.value = AuthState.Authenticated
                            } else {
                                sendEmailVerification()
                                auth.signOut()
                                _authState.value = AuthState.Error("Email not verified. Check your inbox.")
                            }
                        } else {
                            _authState.value = AuthState.Error("Failed to refresh user data.")
                        }
                    }
                } else {
                    _authState.value = AuthState.Error(task.exception?.message ?: "Something went wrong")
                }
            }
    }

    private fun sendEmailVerification() {
        val user = auth.currentUser
        user?.sendEmailVerification()
            ?.addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    Log.d(TAG, "Email sent.")
                }
            }
    }



    fun sendRecoveryEmail(defaultEmail: String) {
        viewModelScope.launch {
            val isSuccess = if (recoveryEmail.value == defaultEmail) {
                // Simula éxito, pero aquí iría Firebase: auth.sendPasswordResetEmail(recoveryEmail).await()
                true
            } else false

            recoveryMessage.value = if (isSuccess) {
                "Recovery email sent successfully."
            } else {
                "Failed to send recovery email. \n Please check the email address."
            }

            showRecoverDialog.value = false
            showRecoverDialogRes.value = true
        }
    }
}
