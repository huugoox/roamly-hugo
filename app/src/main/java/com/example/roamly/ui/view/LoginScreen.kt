package com.example.roamly.ui.view

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.authentication.ui.viewmodels.AuthState
import com.example.roamly.R
import com.example.roamly.ui.viewmodel.LoginViewModel

@Composable
fun LoginScreen(
    navController: NavController,
    viewModel: LoginViewModel = hiltViewModel()
) {
    val email by viewModel.email.collectAsState()
    val password by viewModel.password.collectAsState()
    val showAlert by viewModel.showAlert.collectAsState()
    val showRecoverDialog by viewModel.showRecoverDialog.collectAsState()
    val showRecoverDialogRes by viewModel.showRecoverDialogRes.collectAsState()
    val recoveryEmail by viewModel.recoveryEmail.collectAsState()
    val recoveryMessage by viewModel.recoveryMessage.collectAsState()
    val authState by viewModel.authState.observeAsState()
    val defaultEmail = stringResource(id = R.string.default_email)

    // Navegación reactiva basada en authState
    LaunchedEffect(authState) {
        when (authState) {
            is AuthState.Authenticated -> {
                navController.navigate("home") {
                    popUpTo("login") { inclusive = true }
                }
            }
            is AuthState.Error -> {
                viewModel.setShowAlert(true)
            }
            else -> {}
        }
    }

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
                onValueChange = viewModel::onEmailChange,
                label = { Text(stringResource(id = R.string.email)) },
                singleLine = true,
                modifier = Modifier.fillMaxWidth(),
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
                onValueChange = viewModel::onPasswordChange,
                label = { Text(stringResource(id = R.string.password)) },
                singleLine = true,
                visualTransformation = PasswordVisualTransformation(),
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
                modifier = Modifier.fillMaxWidth(),
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

            Spacer(modifier = Modifier.height(20.dp))

            Button(
                onClick = {
                    viewModel.login(email, password)
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(50.dp),
                shape = RoundedCornerShape(12.dp),
                colors = ButtonDefaults.buttonColors(containerColor = Color.Black)
            ) {
                Text(text = stringResource(id = R.string.login), color = Color.White, fontSize = 18.sp)
            }

            Spacer(modifier = Modifier.height(10.dp))

            TextButton(onClick = { viewModel.setShowRecoverDialog(true) }) {
                Text(text = stringResource(id = R.string.forgot_password), color = Color.Gray)
            }

            TextButton(onClick = { navController.navigate("register") }) {
                Text(text = stringResource(id = R.string.dont_have_account), color = Color.Gray)
            }
        }

        if (showAlert) {
            AlertDialog(
                onDismissRequest = { viewModel.setShowAlert(false) },
                containerColor = Color.White,
                title = { Text(stringResource(id = R.string.login_failed), color = Color.Black) },
                text = { Text(stringResource(id = R.string.invalid_username_password), color = Color.Black) },
                confirmButton = {
                    Button(
                        onClick = { viewModel.setShowAlert(false) },
                        colors = ButtonDefaults.buttonColors(containerColor = Color.Black, contentColor = Color.White)
                    ) {
                        Text("OK")
                    }
                }
            )
        }

        if (showRecoverDialog) {
            AlertDialog(
                onDismissRequest = { viewModel.setShowRecoverDialog(false) },
                containerColor = Color.White,
                title = { Text(stringResource(id = R.string.recover_password), color = Color.Black) },
                text = {
                    Column {
                        Text(stringResource(id = R.string.enter_email_recovery), color = Color.Black)
                        Spacer(modifier = Modifier.height(10.dp))
                        OutlinedTextField(
                            value = recoveryEmail,
                            onValueChange = viewModel::onRecoveryEmailChange,
                            label = { Text(stringResource(id = R.string.email)) },
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
                        onClick = { viewModel.sendRecoveryEmail(defaultEmail) },
                        colors = ButtonDefaults.buttonColors(containerColor = Color.Black, contentColor = Color.White)
                    ) {
                        Text(stringResource(id = R.string.send))
                    }
                },
                dismissButton = {
                    Button(
                        onClick = { viewModel.setShowRecoverDialog(false) },
                        colors = ButtonDefaults.buttonColors(containerColor = Color.Black, contentColor = Color.White)
                    ) {
                        Text(stringResource(id = R.string.cancel))
                    }
                }
            )
        }

        if (showRecoverDialogRes) {
            AlertDialog(
                onDismissRequest = { viewModel.setShowRecoverDialogRes(false) },
                containerColor = Color.White,
                title = { Text(stringResource(id = R.string.recovery_status), color = Color.Black) },
                text = { Text(recoveryMessage, color = Color.Black) },
                confirmButton = {
                    Button(
                        onClick = { viewModel.setShowRecoverDialogRes(false) },
                        colors = ButtonDefaults.buttonColors(containerColor = Color.Black, contentColor = Color.White)
                    ) {
                        Text("OK")
                    }
                }
            )
        }
    }
}

