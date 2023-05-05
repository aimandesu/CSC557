package com.example.csc557

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.Composable
import androidx.navigation.NavType
//import androidx.compose.ui.graphics.BlendMode.Companion.Screen
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.csc557.ui.theme.cardetails.carDetails
import com.example.csc557.ui.theme.home.home

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
//           home()
//            carDetails()
            navigation()
        }
    }
}

@Composable
fun navigation() {
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = Screen.HomeScreen.route) {
        composable(route = Screen.HomeScreen.route) {
            home(navController = navController)
        }
        composable(
            route = Screen.CarDetailScreen.route + "/{nameKey}",
            arguments = listOf(
                navArgument("nameKey") {
                    type = NavType.StringType
                }
            )
        ) {
          navBackStackEntry ->
            carDetails(carModel = navBackStackEntry.arguments?.getString("nameKey"), navController)
        }
    }
}