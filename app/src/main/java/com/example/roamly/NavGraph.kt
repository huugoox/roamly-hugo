package com.example.roamly


import ConfigureProfileScreen
import SettingsScreen
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.example.roamly.ui.view.AboutScreen
import com.example.roamly.ui.view.HomeScreenScaffold2
import com.example.roamly.ui.view.LoginScreen2
import com.example.roamly.ui.view.ProfileScreen
import com.example.roamly.ui.view.RegisterScreen
import com.example.roamly.ui.view.TermsAndConditionsScreen




@Composable
fun NavGraph(navController: NavHostController) {
    NavHost(navController = navController, startDestination = "login") {
        composable("login") { LoginScreen2(navController) }
        composable("home") { HomeScreenScaffold2(navController)
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
    }
}
