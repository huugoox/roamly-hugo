package com.example.navigation.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.outlined.Settings
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreenScaffold(navController: NavController) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Home Screen") },
                actions = {
                    // Botón de la top bar, por ejemplo, para acceder a Settings
                    IconButton(onClick = { /* Acción para el botón de la top bar */ }) {
                        Icon(Icons.Outlined.Settings, contentDescription = "Settings")
                    }
                }
            )
        },
        floatingActionButton = {
            FloatingActionButton(
                onClick = { /* Acción del FAB, por ejemplo, abrir menú o realizar otra acción */ }
            ) {
                Icon(Icons.Filled.Menu, contentDescription = "FAB Menu")
            }
        },
        content = { padding ->
            // Contenido de la pantalla con un botón en la parte superior (top button)
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(padding)
                    .padding(16.dp)
            ) {
                // Botón en la parte superior del contenido
                Button(
                    onClick = { /* Acción del top button */ },
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Text("Top Button")
                }
                Spacer(modifier = Modifier.height(20.dp))
                // Contenido principal
                Text(
                    text = "Bienvenido a la Home Screen",
                    style = MaterialTheme.typography.headlineMedium,
                    modifier = Modifier.align(Alignment.CenterHorizontally)
                )
                Spacer(modifier = Modifier.height(20.dp))
                Button(
                    onClick = { navController.navigate("profile") },
                    modifier = Modifier.align(Alignment.CenterHorizontally)
                ) {
                    Text("Ir al Perfil")
                }
            }
        }
    )
}