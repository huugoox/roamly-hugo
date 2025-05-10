package com.example.authentication.ui.viewmodels

import android.content.Context
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.ktx.Firebase

class AuthViewModel : ViewModel() {

    private val TAG = "AuthViewModel"
    private val auth : FirebaseAuth = FirebaseAuth.getInstance()

    private val _authState = MutableLiveData<AuthState>()
    val authState: LiveData<AuthState> = _authState

    init {
        checkAuthStatus()
    }


    fun sendEmailVerification() {
        // [START send_email_verification]
        val user = auth.currentUser

        user!!.sendEmailVerification()
            .addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    Log.d(TAG, "Email sent.")
                }
            }
        // [END send_email_verification]
    }

    fun checkAuthStatus(){
        if(auth.currentUser==null){
            _authState.value = AuthState.Unauthenticated
        }else{
            _authState.value = AuthState.Authenticated
        }
    }

    fun login(email : String,password : String){

        //https://firebase.google.com/docs/auth/web/manage-users?hl=es-419
//        val user = auth.currentUser
//
//        user?.let {
//            // Name, email address, and profile photo Url
//            val name = it.displayName
//            val email = it.email
//            val photoUrl = it.photoUrl
//
//            // Check if user's email is verified
//            val emailVerified = it.isEmailVerified
//
//            // The user's ID, unique to the Firebase project. Do NOT use this value to
//            // authenticate with your backend server, if you have one. Use
//            // FirebaseUser.getIdToken() instead.
//            val uid = it.uid
//        }

        if(email.isEmpty() || password.isEmpty()){
            _authState.value = AuthState.Error("Email or password can't be empty")
            return
        }


        _authState.value = AuthState.Loading
        auth.signInWithEmailAndPassword(email,password)
            .addOnCompleteListener{task->
//                if (task.isSuccessful){
//                    _authState.value = AuthState.Authenticated
//                }else{
//                    _authState.value = AuthState.Error(task.exception?.message?:"Something went wrong")
//                }

                if (task.isSuccessful) {

                    val user = auth.currentUser

                    if (user != null && user.isEmailVerified) {
                        _authState.value = AuthState.Authenticated
                    } else {
                        _authState.value = AuthState.Error("Email address not verified. Please check your email before continuing.")
                        sendEmailVerification()
                        auth.signOut() // opcional: cerrar sesión si no está verificado
                    }
                } else {
                    _authState.value = AuthState.Error(task.exception?.message ?: "Something went wrong")
                }
            }
    }

    fun signup(email : String,password : String){

        if(email.isEmpty() || password.isEmpty()){
            _authState.value = AuthState.Error("Email or password can't be empty")
            return
        }
        _authState.value = AuthState.Loading
        auth.createUserWithEmailAndPassword(email,password)
            .addOnCompleteListener{task->
                if (task.isSuccessful){
                    sendEmailVerification()
                    _authState.value = AuthState.Error("Please, confirm your email")
//                    _authState.value = AuthState.Authenticated
                    //sendEmailVerification()
                }else{
                    _authState.value = AuthState.Error(task.exception?.message?:"Something went wrong")
                }
            }
    }

    fun signout(){
        auth.signOut()
        _authState.value = AuthState.Unauthenticated
    }


}


sealed class AuthState{
    object Authenticated : AuthState()
    object Unauthenticated : AuthState()
    object Loading : AuthState()
    data class Error(val message : String) : AuthState()
}