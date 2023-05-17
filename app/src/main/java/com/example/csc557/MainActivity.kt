package com.example.csc557

import android.app.Activity.RESULT_OK
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.compose.setContent
import androidx.activity.result.contract.ActivityResultContracts
import androidx.activity.viewModels
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
//import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.csc557.cartscreen.cartScreen
import com.example.csc557.profile.profileScreen
import com.example.csc557.ui.theme.allcars.allCars
import com.example.csc557.ui.theme.boardinglogin.BoardingLogin
//import com.example.csc557.ui.theme.boardinglogin.SignInViewModel
import com.example.csc557.ui.theme.cardetails.carDetails
import com.example.csc557.ui.theme.home.home
import com.example.csc557.ui.theme.payment.payment
import com.example.csc557.ui.theme.search.search

class MainActivity : ComponentActivity() {
    private val sharedViewModel: SharedViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
//            navigation(sharedViewModel)
            navigation(sharedViewModel)
//            BoardingLogin()
        }
    }
}

@Composable
fun navigation(sharedViewModel: SharedViewModel) {
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = Screen.HomeScreen.route) {
//        composable(route = Screen.BoardingScreenSign.route){
//            val viewModel = viewModel<SignInViewModel>()
//            val state by viewModel.state.collectAsStateWithLifecycle()
//            val launcher = rememberLauncherForActivityResult(
//                contract = ActivityResultContracts.StartIntentSenderForResult(),
//                onResult = {
//                    result ->
//                    if(result.resultCode == RESULT_OK){
////                        life
//                    }
//                })
//        }
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
                    type = NavType.StringType
                },
            )
        ) { navBackStackEntry ->
            carDetails(
                carModel = navBackStackEntry.arguments?.getString("carModel"),
                carBrand = navBackStackEntry.arguments?.getString("carBrand"),
                navController,sharedViewModel)
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
            search(model = navBackStackEntry.arguments?.getString("searchItem"), navController, sharedViewModel)
        }
        composable(route = Screen.AllCarsScreen.route){
            allCars(navController = navController, sharedViewModel)
        }
        composable(route = Screen.CartScreen.route){
            cartScreen(navController)
        }
        composable(route = Screen.ProfileScreen.route){
            profileScreen(navController)
        }
    }
}