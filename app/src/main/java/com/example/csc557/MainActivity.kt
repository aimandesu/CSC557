package com.example.csc557

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.runtime.Composable
import androidx.navigation.NavType
//import androidx.compose.ui.graphics.BlendMode.Companion.Screen
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.csc557.ui.theme.allcars.allCars
import com.example.csc557.ui.theme.cardetails.carDetails
import com.example.csc557.ui.theme.home.home
import com.example.csc557.ui.theme.payment.payment
import com.example.csc557.ui.theme.search.search
import com.example.csc557.ui.theme.testing.testing

class MainActivity : ComponentActivity() {
    private val sharedViewModel: SharedViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
//           home()
//            carDetails()
            navigation(sharedViewModel)
        }
    }
}

@Composable
fun navigation(sharedViewModel: SharedViewModel) {
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = Screen.HomeScreen.route) {
        composable(route = Screen.HomeScreen.route) {
            home(navController = navController, sharedViewModel)
        }
        composable(
            route = Screen.CarDetailScreen.route + "/{carModel}/{carBrand}",
            arguments = listOf(
                navArgument("carModel") {
                    type = NavType.StringType
                },
                navArgument("carBrand") {
                    type = androidx.navigation.NavType.StringType
                }
            )
        ) { navBackStackEntry ->
            carDetails(carModel = navBackStackEntry.arguments?.getString("carModel"),
                carBrand = navBackStackEntry.arguments?.getString("carBrand"),
                navController)
        }
        composable(route = Screen.PaymentDetailScreen.route) {
            payment(navController = navController)
        }
        composable(route = Screen.SearchScreen.route + "/{searchItem}",
            arguments = listOf(
                navArgument("searchItem") {
                    type = NavType.StringType
                }
            )
        ) {
            navBackStackEntry ->
            search(model = navBackStackEntry.arguments?.getString("searchItem"), navController)
        }
        composable(route = Screen.AllCarsScreen.route){
            allCars(navController = navController)
        }
        composable( route = Screen.Testing.route){
            testing(navController, sharedViewModel)
        }
    }
}