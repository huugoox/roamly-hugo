package com.example.roamly.ui.view

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TermsAndConditionsScreen(navController: NavController) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Terms & Conditions", color = Color.Black) },
                navigationIcon = {
                    IconButton(onClick = { navController.popBackStack() }) {
                        Icon(Icons.AutoMirrored.Filled.ArrowBack, contentDescription = "Back", tint = Color.Black)
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(containerColor = Color.White)
            )
        }
    ) { padding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(Color.White)
                .padding(20.dp)
        ) {
            Text("Terms & Conditions", fontSize = 20.sp, fontWeight = FontWeight.Bold, color = Color.Black)
            Text(
                "By using this app, you agree to our terms and conditions. Please review them carefully.",
                fontSize = 14.sp, color = Color.Black, modifier = Modifier.padding(top = 16.dp)
            )
            Text(
                "1. You must comply with all applicable laws.\n" +
                        "2. We are not responsible for any damages resulting from app usage.\n" +
                        "3. We may update these terms at any time.",
                fontSize = 14.sp, color = Color.Gray, modifier = Modifier.padding(top = 16.dp)
            )
        }
    }
}