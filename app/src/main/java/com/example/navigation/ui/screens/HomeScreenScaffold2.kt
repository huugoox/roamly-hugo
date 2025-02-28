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

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreenScaffold2(navController: NavController) {
    var showSettingsMenu by remember { mutableStateOf(false) }

    // Estado para el elemento seleccionado del bottom app bar
    var selectedIndex by remember { mutableStateOf(0) }

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
                selectedIndex = selectedIndex,
                onItemSelected = {
                    selectedIndex = it
                }
            )
        },
        floatingActionButton = {
            FloatingActionButton(
                onClick = { /* Acción del FAB */ }
            ) {
                Icon(Icons.Filled.Settings, contentDescription = "Fast Configuration")
            }
        }
    ) { padding ->
        // Contenido de la pantalla
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(Color.White)
                .padding(padding)
        ) {
            Text(
                text = "Explore Upcoming Trips",
                style = MaterialTheme.typography.headlineSmall,
                color = Color.Black,
                modifier = Modifier.padding(16.dp)
            )

            TripList(navController)

        }
    }
}
@Composable
fun TripList(navController: NavController) {
    val trips = TripRepository.trips

    LazyColumn(
        modifier = Modifier.fillMaxSize(),
        contentPadding = PaddingValues(16.dp),
        verticalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        items(trips) { trip ->
            TripCard(trip = trip, onClick = {
                // Aquí podrías navegar a una pantalla de detalles del viaje
                navController.navigate("tripDetails/${trip.id}")
            })
        }
    }
}

@Composable
fun TripCard(trip: Trip, onClick: () -> Unit) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .clickable { onClick() },
        elevation = CardDefaults.cardElevation(4.dp)
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Text(text = trip.destination, style = MaterialTheme.typography.titleLarge)
            Text(text = "Start: ${trip.startDate}", style = MaterialTheme.typography.bodyMedium)
            Text(text = "End: ${trip.endDate}", style = MaterialTheme.typography.bodyMedium)
            Text(text = "Budget: \$${trip.budget}", style = MaterialTheme.typography.bodyMedium, color = Color.Gray)
        }
    }
}

@Composable
fun BottomNavigationBar(
    selectedIndex: Int,
    onItemSelected: (Int) -> Unit
) {
    NavigationBar {

        NavigationBarItem(
            icon = { Icon(Icons.Filled.List, contentDescription = "Itinerarios") },
            label = { Text("Itinerarios") },
            selected = selectedIndex == 0,
            onClick = { onItemSelected(0) }
        )
        NavigationBarItem(
            icon = { Icon(Icons.Filled.Search, contentDescription = "Search") },
            label = { Text("Search") },
            selected = selectedIndex == 1,
            onClick = { onItemSelected(1) }
        )
        NavigationBarItem(
            icon = { Icon(Icons.Filled.Explore, contentDescription = "Explore") },
            label = { Text("Explore") },
            selected = selectedIndex == 2,
            onClick = { onItemSelected(2) }
        )
    }
}