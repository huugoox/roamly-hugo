package com.example.roamly.ui.view

import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
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
import com.example.roamly.R
import com.example.roamly.ui.viewmodel.RegisterViewModel

@Composable
fun RegisterScreen(navController: NavController, viewModel: RegisterViewModel = hiltViewModel()) {

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
                modifier = Modifier.size(100.dp)
            )
            Spacer(modifier = Modifier.height(20.dp))

            // Nombre Completo
            OutlinedTextField(
                value = viewModel.fullName,
                onValueChange = { viewModel.onFullNameChanged(it) },
                placeholder = { Text(stringResource(id = R.string.full_name), color = Color.Gray) },
                modifier = Modifier.fillMaxWidth().heightIn(min = 56.dp),
                shape = RoundedCornerShape(12.dp),
                isError = viewModel.fullNameError != null,
                colors = OutlinedTextFieldDefaults.colors(
                    focusedBorderColor = Color.Gray,
                    unfocusedBorderColor = Color.LightGray,
                    focusedTextColor = Color.Black,
                    unfocusedTextColor = Color.Black
                )
            )
            viewModel.fullNameError?.let {
                Text(text = it, color = Color.Red, fontSize = 12.sp)
            }
            Spacer(modifier = Modifier.height(12.dp))

            // Email
            OutlinedTextField(
                value = viewModel.email,
                onValueChange = { viewModel.onEmailChanged(it) },
                placeholder = { Text(stringResource(id = R.string.email), color = Color.Gray) },
                modifier = Modifier.fillMaxWidth().heightIn(min = 56.dp),
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email),
                shape = RoundedCornerShape(12.dp),
                isError = viewModel.emailError != null,
                colors = OutlinedTextFieldDefaults.colors(
                    focusedBorderColor = Color.Gray,
                    unfocusedBorderColor = Color.LightGray,
                    focusedTextColor = Color.Black,
                    unfocusedTextColor = Color.Black
                )
            )
            viewModel.emailError?.let {
                Text(it, color = Color.Red, fontSize = 12.sp)
            }
            Spacer(modifier = Modifier.height(12.dp))

            // Username
            OutlinedTextField(
                value = viewModel.username,
                onValueChange = { viewModel.onUsernameChanged(it) },
                placeholder = { Text("Username", color = Color.Gray) },
                modifier = Modifier.fillMaxWidth().heightIn(min = 56.dp),
                shape = RoundedCornerShape(12.dp),
                isError = viewModel.usernameError != null,
                colors = OutlinedTextFieldDefaults.colors(
                    focusedBorderColor = Color.Gray,
                    unfocusedBorderColor = Color.LightGray,
                    focusedTextColor = Color.Black,
                    unfocusedTextColor = Color.Black
                )
            )
            viewModel.usernameError?.let {
                Text(it, color = Color.Red, fontSize = 12.sp)
            }
            Spacer(modifier = Modifier.height(12.dp))

            // Contraseña
            OutlinedTextField(
                value = viewModel.password,
                onValueChange = { viewModel.onPasswordChanged(it) },
                placeholder = { Text(stringResource(id = R.string.password), color = Color.Gray) },
                modifier = Modifier.fillMaxWidth().heightIn(min = 56.dp),
                visualTransformation = PasswordVisualTransformation(),
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
                shape = RoundedCornerShape(12.dp),
                isError = viewModel.passwordError != null,
                colors = OutlinedTextFieldDefaults.colors(
                    focusedBorderColor = Color.Gray,
                    unfocusedBorderColor = Color.LightGray,
                    focusedTextColor = Color.Black,
                    unfocusedTextColor = Color.Black
                )
            )
            viewModel.passwordError?.let {
                Text(it, color = Color.Red, fontSize = 12.sp)
            }
            Spacer(modifier = Modifier.height(12.dp))

            // Confirmar Contraseña
            OutlinedTextField(
                value = viewModel.confirmPassword,
                onValueChange = { viewModel.onConfirmPasswordChanged(it) },
                placeholder = { Text(stringResource(id = R.string.confirm_password), color = Color.Gray) },
                modifier = Modifier.fillMaxWidth().heightIn(min = 56.dp),
                visualTransformation = PasswordVisualTransformation(),
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
                shape = RoundedCornerShape(12.dp),
                isError = viewModel.confirmPasswordError != null,
                colors = OutlinedTextFieldDefaults.colors(
                    focusedBorderColor = Color.Gray,
                    unfocusedBorderColor = Color.LightGray,
                    focusedTextColor = Color.Black,
                    unfocusedTextColor = Color.Black
                )
            )
            viewModel.confirmPasswordError?.let {
                Text(it, color = Color.Red, fontSize = 12.sp)
            }
            Spacer(modifier = Modifier.height(20.dp))

            // Fecha de nacimiento
            OutlinedTextField(
                value = viewModel.birthdate,
                onValueChange = { viewModel.onBirthdateChanged(it) },
                placeholder = { Text("Birthdate (dd/MM/yyyy)", color = Color.Gray) },
                modifier = Modifier.fillMaxWidth().heightIn(min = 56.dp),
                shape = RoundedCornerShape(12.dp),
                isError = viewModel.birthdateError != null
            )
            viewModel.birthdateError?.let {
                Text(it, color = Color.Red, fontSize = 12.sp)
            }
            Spacer(modifier = Modifier.height(12.dp))

            // Dirección
            OutlinedTextField(
                value = viewModel.address,
                onValueChange = { viewModel.onAddressChanged(it) },
                placeholder = { Text("Address", color = Color.Gray) },
                modifier = Modifier.fillMaxWidth().heightIn(min = 56.dp),
                shape = RoundedCornerShape(12.dp)
            )
            Spacer(modifier = Modifier.height(12.dp))

            // País
            OutlinedTextField(
                value = viewModel.country,
                onValueChange = { viewModel.onCountryChanged(it) },
                placeholder = { Text("Country", color = Color.Gray) },
                modifier = Modifier.fillMaxWidth().heightIn(min = 56.dp),
                shape = RoundedCornerShape(12.dp)
            )
            Spacer(modifier = Modifier.height(12.dp))

            // Teléfono
            OutlinedTextField(
                value = viewModel.phoneNumber,
                onValueChange = { viewModel.onPhoneNumberChanged(it) },
                placeholder = { Text("Phone Number", color = Color.Gray) },
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Phone),
                modifier = Modifier.fillMaxWidth().heightIn(min = 56.dp),
                shape = RoundedCornerShape(12.dp)
            )
            Spacer(modifier = Modifier.height(12.dp))

            // Checkbox aceptar emails
            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.fillMaxWidth()
            ) {
                Checkbox(
                    checked = viewModel.acceptEmails,
                    onCheckedChange = { viewModel.onAcceptEmailsChanged(it) }
                )
                Text(text = "Accept to receive emails", fontSize = 14.sp)
            }
            Spacer(modifier = Modifier.height(12.dp))

            // Botón de Registro
            Button(
                onClick = {
                    Log.d("RegisterScreen", "✅ Botón Sign Up presionado")
                    val isValid = viewModel.validateAllFields()
                    Log.d("RegisterScreen", "¿Validación exitosa? $isValid")
                    if (isValid) {
                        viewModel.checkUsernameExists(viewModel.username) { exists ->
                            if (exists) {
                                Log.d("RegisterScreen", "❌ El nombre de usuario ya existe")
                                viewModel.usernameError = "Username already exists"
                            } else {
                                viewModel.onRegisterClicked()
                                Log.d("RegisterScreen", "➡️ Intentando navegar a Login...")
                                navController.navigate("login")
                            }
                        }
                    } else {
                        Log.d("RegisterScreen", "❌ Error en la validación de formulario")
                    }
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(50.dp),
                shape = RoundedCornerShape(12.dp),
                colors = ButtonDefaults.buttonColors(containerColor = Color.Black)
            ) {
                Text(text = stringResource(id = R.string.sign_up), color = Color.White, fontSize = 18.sp)
            }

            Spacer(modifier = Modifier.height(10.dp))

            // Ir a Login
            TextButton(onClick = {
                Log.d("RegisterScreen", "🔙 Botón 'Already have an account' presionado")
                navController.navigate("login")
            }) {
                Text(text = stringResource(id = R.string.already_have_account), color = Color.Gray)
            }
        }
    }
}
