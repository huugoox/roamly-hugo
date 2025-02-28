package com.example.navigation.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.navigation.R
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

@Composable
fun LoginScreen2(navController: NavController) {

    // States for username, password, and the alert dialog
    var username by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var showAlert by remember { mutableStateOf(false) }
    var showRecoverDialog by remember { mutableStateOf(false) }
    var recoveryEmail by remember { mutableStateOf("") }

    // Get default values from strings.xml
    val defaultUser = stringResource(id = R.string.default_user)
    val defaultPass = stringResource(id = R.string.default_pass)

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
            .padding(horizontal = 16.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = stringResource(id = R.string.login),
            style = MaterialTheme.typography.headlineMedium,
            fontSize = 24.sp,
            color = Color.Black
        )
        Spacer(modifier = Modifier.height(20.dp))
        OutlinedTextField(
            value = username,
            onValueChange = { username = it },
            label = { Text(stringResource(id = R.string.username), color = Color.Black)},
            modifier = Modifier.fillMaxWidth()
        )
        Spacer(modifier = Modifier.height(10.dp))
        OutlinedTextField(
            value = password,
            onValueChange = { password = it },
            label = { Text(stringResource(id = R.string.password), color = Color.Black) },
            modifier = Modifier.fillMaxWidth(),
            visualTransformation = PasswordVisualTransformation(),
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password)
        )
        Spacer(modifier = Modifier.height(20.dp))
        Button(
            onClick = {
                if (username == defaultUser && password == defaultPass) {
                    // Navigate to Home and remove Login from the back stack
                    navController.navigate("home") {
                        popUpTo("login") { inclusive = true }
                    }
                } else {
                    showAlert = true
                }
            },
            modifier = Modifier.fillMaxWidth(),
            colors = ButtonDefaults.buttonColors(containerColor = Color.DarkGray, contentColor = Color.White)
        ) {
            Text(text = "Login")
        }

        Spacer(modifier = Modifier.height(10.dp))

        TextButton(onClick = { showRecoverDialog = true}) {
            Text(text = "¿Forgotten password?", color = Color.DarkGray)
        }
    }

    // Show an alert dialog if the login fails
    if (showAlert) {
        AlertDialog(
            onDismissRequest = { showAlert = false },
            title = { Text("Login Failed") },
            text = { Text("Invalid username or password.") },
            confirmButton = {
                Button(onClick = { showAlert = false }, colors = ButtonDefaults.buttonColors(containerColor = Color.DarkGray, contentColor = Color.White)) {
                    Text("OK")
                }
            }
        )
    }

    if (showRecoverDialog) {
        AlertDialog(
            onDismissRequest = { showRecoverDialog = false },
            title = { Text("Recover Password", color = Color.Black) },
            text = {
                Column {
                    Text("Enter your email to receive password recovery instructions.", color = Color.Black)
                    Spacer(modifier = Modifier.height(10.dp))
                    OutlinedTextField(
                        value = recoveryEmail,
                        onValueChange = { recoveryEmail = it },
                        label = { Text("Email", color = Color.Black) },
                        modifier = Modifier.fillMaxWidth(),
                        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email)
                    )
                }
            },
            confirmButton = {
                Button(onClick = {
                    CoroutineScope(Dispatchers.IO).launch {
                        val isSuccess = sendRecoveryEmail(recoveryEmail)
                        withContext(Dispatchers.Main) {
                            val recoveryMessage = if (isSuccess) {
                                "Recovery email sent successfully."
                            } else {
                                "Failed to send recovery email."
                            }
                        }
                    }
                }, colors = ButtonDefaults.buttonColors(containerColor = Color.DarkGray, contentColor = Color.White)) {
                    Text("Send")
                }
            },
            dismissButton = {
                Button(onClick = { showRecoverDialog = false }, colors = ButtonDefaults.buttonColors(containerColor = Color.DarkGray, contentColor = Color.White)) {
                    Text("Cancel")
                }
            }
        )
    }
}

suspend fun sendRecoveryEmail(email: String): Boolean {
    return try {
        true
    } catch (e: Exception) {
        false
    }
}
