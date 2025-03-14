package com.example.navigation.ui.view

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
import com.example.navigation.R
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LoginScreen2(navController: NavController) {

    // States for username, password, and the alert dialog
    var username by remember { mutableStateOf("p") }
    var password by remember { mutableStateOf("1") }
    var showAlert by remember { mutableStateOf(false) }
    var showRecoverDialog by remember { mutableStateOf(false) }
    var showRecoverDialogRes by remember { mutableStateOf(false) }

    var recoveryEmail by remember { mutableStateOf("") }
    var recoveryMessage by remember { mutableStateOf("") }

    // Get default values from strings.xml
    val defaultUser = stringResource(id = R.string.default_user)
    val defaultPass = stringResource(id = R.string.default_pass)
    val defaultEmail = stringResource(id = R.string.default_email)

    Box (modifier = Modifier
            .fillMaxSize()
            .background(Color.White),
        contentAlignment = Alignment.Center
    ) {
        Column(
            modifier = Modifier
                .padding(20.dp),
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
                value = username,
                onValueChange = { username = it },
                label = { Text(stringResource(id = R.string.username))},
                modifier = Modifier
                    .fillMaxWidth()
                    .background(Color.Transparent),
                shape = RoundedCornerShape(12.dp),
                colors = TextFieldDefaults.outlinedTextFieldColors(
                    focusedTextColor = Color.Black,
                    unfocusedTextColor = Color.Black,
                    focusedBorderColor = Color.Gray,
                    unfocusedBorderColor = Color.LightGray,
                    cursorColor = Color.Black, // Color del cursor
                    focusedLabelColor = Color.Gray, // Color del label cuando está en foco
                    unfocusedLabelColor = Color.DarkGray
                )
            )
            Spacer(modifier = Modifier.height(12.dp))

            OutlinedTextField(
                value = password,
                onValueChange = { password = it },
                label = { Text(stringResource(id = R.string.password)) },
                modifier = Modifier
                    .fillMaxWidth()
                    .background(Color.Transparent),
                visualTransformation = PasswordVisualTransformation(),
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
                shape = RoundedCornerShape(12.dp),
                colors = TextFieldDefaults.outlinedTextFieldColors(
                    focusedBorderColor = Color.Gray,
                    unfocusedBorderColor = Color.LightGray,
                    focusedTextColor = Color.Black, // Color del texto cuando está en foco
                    unfocusedTextColor = Color.Black, // Color del texto cuando no está en foco
                    cursorColor = Color.Black, // Color del cursor
                    focusedLabelColor = Color.Gray, // Color del label cuando está en foco
                    unfocusedLabelColor = Color.DarkGray // Color del label cuando no está en foco
                )
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
                modifier = Modifier
                    .fillMaxWidth()
                    .height(50.dp),
                shape = RoundedCornerShape(12.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color.Black,
                )
            ) {
                Text(text = "Login", color = Color.White, fontSize = 18.sp)
            }

            Spacer(modifier = Modifier.height(10.dp))

            TextButton(onClick = { showRecoverDialog = true }) {
                Text(text = "¿Forgotten password?", color = Color.Gray)
            }
        }

        // Show an alert dialog if the login fails
        if (showAlert) {
            AlertDialog(
                onDismissRequest = { showAlert = false },
                containerColor = Color.White,
                title = { Text("Login Failed", color = Color.Black) },
                text = { Text("Invalid username or password.", color = Color.Black) },
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
                title = { Text("Recover Password", color = Color.Black) },
                text = {
                    Column {
                        Text(
                            "Enter your email to receive password recovery instructions.",
                            color = Color.Black
                        )
                        Spacer(modifier = Modifier.height(10.dp))
                        OutlinedTextField(
                            value = recoveryEmail,
                            onValueChange = { recoveryEmail = it },
                            label = { Text("Email", color = Color.LightGray) },
                            modifier = Modifier.fillMaxWidth(),
                            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email),
                            shape = RoundedCornerShape(12.dp),
                            colors = TextFieldDefaults.outlinedTextFieldColors(
                                focusedBorderColor = Color.Gray,
                                unfocusedBorderColor = Color.LightGray,
                                focusedTextColor = Color.Black, // Color del texto cuando está en foco
                                unfocusedTextColor = Color.Black, // Color del texto cuando no está en foco
                                cursorColor = Color.Black, // Color del cursor
                                focusedLabelColor = Color.Gray, // Color del label cuando está en foco
                                unfocusedLabelColor = Color.DarkGray // Color del label cuando no está en foco
                            )
                        )
                    }
                },
                confirmButton = {
                    Button(
                        onClick = { // @ToDo
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
                                        "Failed to send recovery email. \n" +
                                                " Please check the email address."
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
                        Text("Send")
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
                        Text("Cancel")
                    }
                }
            )
        }

        Spacer(modifier = Modifier.height(10.dp))

        if (showRecoverDialogRes) {
            AlertDialog(
                onDismissRequest = {
                    showRecoverDialogRes = false
                    showRecoverDialog = false },
                containerColor = Color.White,
                title = { Text("Recovery Status", color = Color.Black) },
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

suspend fun sendRecoveryEmail(email: String): Boolean {
    return try {
        true
    } catch (e: Exception) {
        false
    }
}
