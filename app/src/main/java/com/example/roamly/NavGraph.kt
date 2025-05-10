package com.example.roamly


import ConfigureProfileScreen
import SettingsScreen
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.example.authentication.ui.viewmodels.AuthViewModel
import com.example.roamly.ui.view.AboutScreen
import com.example.roamly.ui.view.HomeScreen
import com.example.roamly.ui.view.ItineraryScreen
import com.example.roamly.ui.view.ProfileScreen
import com.example.roamly.ui.view.RegisterScreen
import com.example.roamly.ui.view.TermsAndConditionsScreen
import com.example.roamly.ui.view.LoginScreen


@Composable
fun NavGraph(navController: NavHostController) {
    NavHost(navController = navController, startDestination = "login") {
        composable("login") { LoginScreen(navController) }
        composable("home") { HomeScreen(navController)
        }
        composable("profile") { ProfileScreen(navController) }
        composable("profile/{id}",
                arguments = listOf(navArgument("id"){
                            type = NavType.IntType
                })
        ) {
            val userId = it.arguments?.getInt("id") ?: -1
            ProfileScreen(navController)
        }
        composable("profileMenu") { ProfileScreen(navController) }
        composable("about") { AboutScreen(navController)  }
        composable("termsAndConditions"){ TermsAndConditionsScreen(navController) }
        composable ("settings"){ SettingsScreen(navController)  }
        composable("configureProfile") { ConfigureProfileScreen(navController)}
        composable("register") { RegisterScreen(navController)}

        composable("itinerary/{tripId}",
            arguments = listOf(navArgument("tripId") { type = NavType.StringType })
        ) { backStackEntry ->
            val tripIdString = backStackEntry.arguments?.getString("tripId") ?: return@composable
            val tripId = tripIdString.toIntOrNull() ?: -1
            val tripName = navController.currentBackStackEntry?.savedStateHandle?.get<String>("tripName") ?: "Trip"

            ItineraryScreen(navController, tripId, tripName)
        }
    }
}
