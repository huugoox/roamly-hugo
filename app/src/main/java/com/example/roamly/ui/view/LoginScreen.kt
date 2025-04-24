package com.example.roamly.ui.view

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import android.util.Log
import com.example.roamly.R
import com.google.firebase.auth.FirebaseAuth
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.tasks.await
import kotlinx.coroutines.withContext

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LoginScreen(navController: NavController) {

    // States for email, password, and the alert dialog
    var email by remember { mutableStateOf("hfs2@alumnes.udl.cat") }
    var password by remember { mutableStateOf("123456") }
    var showAlert by remember { mutableStateOf(false) }
    var showRecoverDialog by remember { mutableStateOf(false) }
    var showRecoverDialogRes by remember { mutableStateOf(false) }

    var recoveryEmail by remember { mutableStateOf("") }
    var recoveryMessage by remember { mutableStateOf("") }

    // Default email for password recovery placeholder
    val defaultEmail = stringResource(id = R.string.default_email)

    // Firebase Auth instance
    val auth = remember { FirebaseAuth.getInstance() }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White),
        contentAlignment = Alignment.Center
    ) {
        Column(
            modifier = Modifier.padding(20.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Image(
                painter = painterResource(id = R.drawable.finalroamlylogo_3),
                contentDescription = "Roamly Logo",
                modifier = Modifier.size(120.dp)
            )
            Text(
                text = stringResource(id = R.string.welcome_back),
                style = MaterialTheme.typography.headlineMedium,
                fontSize = 28.sp,
                color = Color.Black
            )
            Spacer(modifier = Modifier.height(20.dp))

            OutlinedTextField(
                value = email,
                onValueChange = { email = it },
                label = { Text(stringResource(id = R.string.email)) },
                singleLine = true,
                modifier = Modifier
                    .fillMaxWidth()
                    .background(Color.Transparent),
                shape = RoundedCornerShape(12.dp),
                colors = OutlinedTextFieldDefaults.colors(
                    focusedTextColor = Color.Black,
                    unfocusedTextColor = Color.Black,
                    focusedBorderColor = Color.Gray,
                    unfocusedBorderColor = Color.LightGray,
                    cursorColor = Color.Black,
                    focusedLabelColor = Color.Gray,
                    unfocusedLabelColor = Color.DarkGray
                )
            )
            Spacer(modifier = Modifier.height(12.dp))

            OutlinedTextField(
                value = password,
                onValueChange = { password = it },
                label = { Text(stringResource(id = R.string.password)) },
                singleLine = true,
                visualTransformation = PasswordVisualTransformation(),
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
                modifier = Modifier
                    .fillMaxWidth()
                    .background(Color.Transparent),
                shape = RoundedCornerShape(12.dp),
                colors = OutlinedTextFieldDefaults.colors(
                    focusedBorderColor = Color.Gray,
                    unfocusedBorderColor = Color.LightGray,
                    focusedTextColor = Color.Black,
                    unfocusedTextColor = Color.Black,
                    cursorColor = Color.Black,
                    focusedLabelColor = Color.Gray,
                    unfocusedLabelColor = Color.DarkGray
                )
            )
            Spacer(modifier = Modifier.height(20.dp))

            Button(
                onClick = {
                    // Attempt Firebase login
                    CoroutineScope(Dispatchers.IO).launch {
                        try {
                            auth.signInWithEmailAndPassword(email, password).await()
                            withContext(Dispatchers.Main) {
                                Log.d("LoginScreen", "Login successful")
                                navController.navigate("home") {
                                    popUpTo("login") { inclusive = true }
                                }
                            }
                        } catch (e: Exception) {
                            Log.e("LoginScreen", "Login failed", e)
                            withContext(Dispatchers.Main) {
                                showAlert = true
                            }
                        }
                    }
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(50.dp),
                shape = RoundedCornerShape(12.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color.Black
                )
            ) {
                Text(
                    text = stringResource(id = R.string.login),
                    color = Color.White,
                    fontSize = 18.sp
                )
            }

            Spacer(modifier = Modifier.height(10.dp))

            TextButton(onClick = { showRecoverDialog = true }) {
                Text(text = stringResource(id = R.string.forgot_password), color = Color.Gray)
            }
            TextButton(onClick = { navController.navigate("register") }) {
                Text(text = stringResource(id = R.string.dont_have_account), color = Color.Gray)
            }
        }

        // Show an alert dialog if the login fails
        if (showAlert) {
            AlertDialog(
                onDismissRequest = { showAlert = false },
                containerColor = Color.White,
                title = { Text(stringResource(id = R.string.login_failed), color = Color.Black) },
                text = { Text(stringResource(id = R.string.invalid_username_password), color = Color.Black) },
                confirmButton = {
                    Button(
                        onClick = { showAlert = false },
                        colors = ButtonDefaults.buttonColors(
                            containerColor = Color.Black,
                            contentColor = Color.White
                        )
                    ) {
                        Text("OK")
                    }
                }
            )
        }

        if (showRecoverDialog) {
            AlertDialog(
                onDismissRequest = { showRecoverDialog = false },
                containerColor = Color.White,
                title = { Text(stringResource(id = R.string.recover_password), color = Color.Black) },
                text = {
                    Column {
                        Text(
                            stringResource(id = R.string.enter_email_recovery),
                            color = Color.Black
                        )
                        Spacer(modifier = Modifier.height(10.dp))
                        OutlinedTextField(
                            value = recoveryEmail,
                            onValueChange = { recoveryEmail = it },
                            label = { Text(stringResource(id = R.string.email), color = Color.LightGray) },
                            modifier = Modifier.fillMaxWidth(),
                            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email),
                            shape = RoundedCornerShape(12.dp),
                            colors = OutlinedTextFieldDefaults.colors(
                                focusedBorderColor = Color.Gray,
                                unfocusedBorderColor = Color.LightGray,
                                focusedTextColor = Color.Black,
                                unfocusedTextColor = Color.Black,
                                cursorColor = Color.Black,
                                focusedLabelColor = Color.Gray,
                                unfocusedLabelColor = Color.DarkGray
                            )
                        )
                    }
                },
                confirmButton = {
                    Button(
                        onClick = {
                            CoroutineScope(Dispatchers.IO).launch {
                                val isSuccess = if (recoveryEmail == defaultEmail) {
                                    sendRecoveryEmail(recoveryEmail)
                                } else {
                                    false
                                }
                                withContext(Dispatchers.Main) {
                                    recoveryMessage = if (isSuccess) {
                                        "Recovery email sent successfully."
                                    } else {
                                        "Failed to send recovery email. \n Please check the email address."
                                    }
                                    showRecoverDialog = false
                                    showRecoverDialogRes = true

                                }
                            }
                        },
                        colors = ButtonDefaults.buttonColors(
                            containerColor = Color.Black,
                            contentColor = Color.White
                        )
                    ) {
                        Text(stringResource(id = R.string.send))
                    }
                },
                dismissButton = {
                    Button(
                        onClick = { showRecoverDialog = false },
                        colors = ButtonDefaults.buttonColors(
                            containerColor = Color.Black,
                            contentColor = Color.White
                        )
                    ) {
                        Text(stringResource(id = R.string.cancel))
                    }
                }
            )
        }

        Spacer(modifier = Modifier.height(10.dp))

        if (showRecoverDialogRes) {
            AlertDialog(
                onDismissRequest = {
                    showRecoverDialogRes = false
                    showRecoverDialog = false
                },
                containerColor = Color.White,
                title = { Text(stringResource(id = R.string.recovery_status), color = Color.Black) },
                text = { Text(recoveryMessage, color = Color.Black) },
                confirmButton = {
                    Button(onClick = { showRecoverDialogRes = false },                        colors = ButtonDefaults.buttonColors(
                        containerColor = Color.Black,
                        contentColor = Color.White
                    )) {
                        Text("OK")
                    }
                }
            )
        }
    }
}

// Placeholder for actual recovery implementation
suspend fun sendRecoveryEmail(email: String): Boolean {
    return try {
        true
    } catch (e: Exception) {
        false
    }
}
