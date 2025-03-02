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
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Build
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material.icons.filled.Explore
import androidx.compose.material.icons.filled.Favorite
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
    EXPLORE, SEARCH, FAVORITES, COMPLETED
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreenScaffold2(navController: NavController) {
    var selectedMode by remember { mutableStateOf(PlanningMode.EXPLORE) }

    Scaffold(
        topBar = {
            TopAppBar(title = { Text("Roamly", color = Color.Black) },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = Color.White
                )
            )
        },
        bottomBar = {
            BottomNavigationBar(selectedMode) { selectedMode = it }
        },
        floatingActionButton = {
            FloatingActionButton(
                onClick = { navController.navigate("create_trip") },
                containerColor = MaterialTheme.colorScheme.primary
            ) {
                Icon(Icons.Filled.Add, contentDescription = "Create Trip")
            }
        }
    ) { padding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(Color.White)
                .padding(padding)
        ) {
            when (selectedMode) {
                PlanningMode.EXPLORE -> GlobeScreen()
                PlanningMode.SEARCH -> SearchScreen()
                PlanningMode.FAVORITES -> FavoriteTripsScreen()
                PlanningMode.COMPLETED -> CompletedTripsScreen()
            }
        }
    }
}

@Composable
fun BottomNavigationBar(selectedMode: PlanningMode, onModeSelected: (PlanningMode) -> Unit) {
    NavigationBar (containerColor = Color.White){
        NavigationBarItem(
            icon = { Icon(Icons.Filled.Explore, contentDescription = "Explore", tint = Color.Black) },
            label = { Text("Explore", color = Color.Black) },
            selected = selectedMode == PlanningMode.EXPLORE,
            onClick = { onModeSelected(PlanningMode.EXPLORE) },
            colors = NavigationBarItemDefaults.colors(
                indicatorColor = Color.LightGray.copy(alpha = 0.2f)
            )
        )
        NavigationBarItem(
            icon = { Icon(Icons.Filled.Search, contentDescription = "Search", tint = Color.Black) },
            label = { Text("Search", color = Color.Black) },
            selected = selectedMode == PlanningMode.SEARCH,
            onClick = { onModeSelected(PlanningMode.SEARCH) },
            colors = NavigationBarItemDefaults.colors(
                indicatorColor = Color.LightGray.copy(alpha = 0.2f)
            )
        )
        NavigationBarItem(
            icon = { Icon(Icons.Filled.Favorite, contentDescription = "Favorites", tint = Color.Black) },
            label = { Text("Favorites", color = Color.Black) },
            selected = selectedMode == PlanningMode.FAVORITES,
            onClick = { onModeSelected(PlanningMode.FAVORITES) },
            colors = NavigationBarItemDefaults.colors(
                indicatorColor = Color.LightGray.copy(alpha = 0.2f)
            )
        )
        NavigationBarItem(
            icon = { Icon(Icons.Filled.CheckCircle, contentDescription = "Completed", tint = Color.Black) },
            label = { Text("Completed", color = Color.Black) },
            selected = selectedMode == PlanningMode.COMPLETED,
            onClick = { onModeSelected(PlanningMode.COMPLETED) },
            colors = NavigationBarItemDefaults.colors(
                indicatorColor = Color.LightGray.copy(alpha = 0.2f)
            )
        )
    }
}