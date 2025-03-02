package com.example.navigation.ui.screens

import  com.example.navigation.data.TripRepository
import com.example.navigation.models.Trip

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Build
import androidx.compose.material.icons.filled.Explore
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.List
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material.icons.filled.Star
import androidx.compose.material.icons.outlined.Settings
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController

enum class PlanningMode {
    EXPLORE, SEARCH, TRIPS
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreenScaffold2(navController: NavController) {
    var showSettingsMenu by remember { mutableStateOf(false) }
    var selectedMode by remember { mutableStateOf(PlanningMode.EXPLORE) }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Roamly Home") },
                navigationIcon = {
                    IconButton(onClick = { /* Acción del menú lateral */ }) {
                        Icon(Icons.Filled.Menu, contentDescription = "Menu")
                    }
                },
                actions = {
                    Box {
                        IconButton(onClick = { showSettingsMenu = !showSettingsMenu }) {
                            Icon(Icons.Outlined.Settings, contentDescription = "Settings")
                        }
                        DropdownMenu(
                            expanded = showSettingsMenu,
                            onDismissRequest = { showSettingsMenu = false }
                        ) {
                            DropdownMenuItem(
                                leadingIcon = { Icon(Icons.Filled.Info, contentDescription = "About") },
                                text = { Text("About") },
                                onClick = {
                                    showSettingsMenu = false
                                    navController.navigate("about")
                                }
                            )
                            DropdownMenuItem(
                                leadingIcon = { Icon(Icons.Filled.Build, contentDescription = "Version Icon") },
                                text = { Text("Version") },
                                onClick = {
                                    showSettingsMenu = false
                                    navController.navigate("version")
                                }
                            )
                            DropdownMenuItem(
                                leadingIcon = { Icon(Icons.Filled.Person, contentDescription = "Profile") },
                                text = { Text("Profile") },
                                onClick = {
                                    showSettingsMenu = false
                                    navController.navigate("profile")
                                }
                            )
                            DropdownMenuItem(
                                leadingIcon = { Icon(Icons.Filled.Settings, contentDescription = "Settings") },
                                text = { Text("Settings") },
                                onClick = {
                                    showSettingsMenu = false
                                    navController.navigate("settings")
                                }
                            )
                            DropdownMenuItem(
                                leadingIcon = { Icon(Icons.Filled.Star, contentDescription = "Terms and Conditions") },
                                text = { Text("Terms & Conditions") },
                                onClick = {
                                    showSettingsMenu = false
                                    navController.navigate("termsAndConditions")
                                }
                            )
                        }
                    }
                }
            )
        },
        bottomBar = {
            BottomNavigationBar(
                selectedMode = selectedMode,
                onModeSelected = { selectedMode = it }
            )
        },
//        floatingActionButton = {
//            FloatingActionButton(
//                onClick = { /* Acción del FAB */ }
//            ) {
//                Icon(Icons.Filled.Settings, contentDescription = "Fast Configuration")
//            }
//        }
    ) { padding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(Color.White)
                .padding(padding)
        ) {
            when (selectedMode) {
                PlanningMode.EXPLORE -> GlobeScreen()
                PlanningMode.SEARCH -> {
                    Text(
                        text = "Search for Trips",
                        style = MaterialTheme.typography.headlineSmall,
                        color = Color.Black,
                        modifier = Modifier.padding(16.dp)
                    )
                    // Aquí iría el composable para la búsqueda
                }
                PlanningMode.TRIPS -> {
                    Text(
                        text = "Your Trips",
                        style = MaterialTheme.typography.headlineSmall,
                        color = Color.Black,
                        modifier = Modifier.padding(16.dp)
                    )
                    // Aquí iría el composable con los itinerarios del usuario
                }
            }
        }
    }
}

@Composable
fun BottomNavigationBar(
    selectedMode: PlanningMode,
    onModeSelected: (PlanningMode) -> Unit
) {
    NavigationBar {
        NavigationBarItem(
            icon = { Icon(Icons.Filled.List, contentDescription = "Itinerarios") },
            label = { Text("Itinerarios") },
            selected = selectedMode == PlanningMode.TRIPS,
            onClick = { onModeSelected(PlanningMode.TRIPS) }
        )
        NavigationBarItem(
            icon = { Icon(Icons.Filled.Search, contentDescription = "Search") },
            label = { Text("Search") },
            selected = selectedMode == PlanningMode.SEARCH,
            onClick = { onModeSelected(PlanningMode.SEARCH) }
        )
        NavigationBarItem(
            icon = { Icon(Icons.Filled.Explore, contentDescription = "Explore") },
            label = { Text("Explore") },
            selected = selectedMode == PlanningMode.EXPLORE,
            onClick = { onModeSelected(PlanningMode.EXPLORE) }
        )
    }
}
