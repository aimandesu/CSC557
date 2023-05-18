package com.example.csc557

import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.compose.setContent
import androidx.activity.result.IntentSenderRequest
import androidx.activity.result.contract.ActivityResultContracts
import androidx.activity.viewModels
import androidx.compose.material.Surface
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.csc557.ui.theme.cartscreen.cartScreen
import com.example.csc557.profile.profileScreen
import com.example.csc557.ui.theme.allcars.allCars
import com.example.csc557.ui.theme.boardinglogin.BoardingLogin
import com.example.csc557.ui.theme.boardinglogin.SignInViewModel
//import com.example.csc557.ui.theme.boardinglogin.SignInViewModel
import com.example.csc557.ui.theme.cardetails.carDetails
import com.example.csc557.ui.theme.home.home
import com.example.csc557.ui.theme.payment.payment
import com.example.csc557.ui.theme.search.search
import androidx.lifecycle.lifecycleScope
import com.example.csc557.ui.theme.boardinglogin.GoogleAuthUiClient
import com.google.android.gms.auth.api.identity.Identity
import kotlinx.coroutines.launch

class MainActivity : ComponentActivity() {
    private val sharedViewModel: SharedViewModel by viewModels()
    private val googleAuthUiClient by lazy {
        GoogleAuthUiClient(
            context = applicationContext,
            oneTapClient = Identity.getSignInClient(applicationContext)

        )
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
//            navigation(sharedViewModel)
            Surface {
                val navController = rememberNavController()
                NavHost(
                    navController = navController,
                    startDestination = Screen.BoardingScreenSign.route
                ) {
                    composable(route = Screen.BoardingScreenSign.route) {
                        val viewModel = viewModel<SignInViewModel>()
                        val state by viewModel.state.collectAsStateWithLifecycle()

                        LaunchedEffect(key1 = Unit) {
                            if (googleAuthUiClient.getSignedInUser() != null) {
                                navController.navigate(Screen.HomeScreen.route)
                            }
                        }

                        val launcher = rememberLauncherForActivityResult(
                            contract = ActivityResultContracts.StartIntentSenderForResult(),
                            onResult = { result ->
                                if (result.resultCode == RESULT_OK) {
                                    lifecycleScope.launch {
                                        val signInResult = googleAuthUiClient.signInWithIntent(
                                            intent = result.data ?: return@launch
                                        )
                                        viewModel.onSignInResult(signInResult)
                                    }
                                }
                            }
                        )

                        LaunchedEffect(key1 = state.isSignInSuccessful) {
                            if (state.isSignInSuccessful) {
                                Toast.makeText(
                                    applicationContext,
                                    "Sign in successful",
                                    Toast.LENGTH_LONG
                                ).show()
                                navController.navigate(Screen.HomeScreen.route)
                                viewModel.resetState()
                            }
                        }

                        BoardingLogin(
                            state = state,
                            onSignInClick = {
                                lifecycleScope.launch {
                                    val signInIntentSender = googleAuthUiClient.signIn()
                                    launcher.launch(
                                        IntentSenderRequest.Builder(
                                            signInIntentSender ?: return@launch
                                        ).build()
                                    )
                                }
                            },
                        )
                    }
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
                            navController, sharedViewModel
                        )
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
                    ) { navBackStackEntry ->
                        search(
                            model = navBackStackEntry.arguments?.getString("searchItem"),
                            navController,
                            sharedViewModel
                        )
                    }
                    composable(route = Screen.AllCarsScreen.route) {
                        allCars(navController = navController, sharedViewModel)
                    }
                    composable(route = Screen.CartScreen.route) {
                        cartScreen(navController)
                    }
                    composable(route = Screen.ProfileScreen.route) {
                        profileScreen(
                            navController,
                            googleAuthUiClient.getSignedInUser(),
                            onSignOut = {
                                lifecycleScope.launch {
                                    googleAuthUiClient.signOut()
                                    Toast.makeText(
                                        applicationContext,
                                        "Signed out",
                                        Toast.LENGTH_LONG
                                    ).show()
//                                navController.popBackStack()
                                    navController.navigate(Screen.BoardingScreenSign.route) {
                                        popUpTo(Screen.BoardingScreenSign.route) {
                                            inclusive = true
                                        }
                                    }
                                }

                            },
                            sharedViewModel,
                        )
                    }
                }
            }

//            navigation(sharedViewModel)
//            BoardingLogin()
        }
    }
}

//@Composable
//fun navigation(sharedViewModel: SharedViewModel) {
//    val navController = rememberNavController()
//    NavHost(navController = navController, startDestination = Screen.HomeScreen.route) {
//        composable(route = Screen.BoardingScreenSign.route){
//            val viewModel = viewModel<SignInViewModel>()
//            val state by viewModel.state.collectAsStateWithLifecycle()
//            val launcher = rememberLauncherForActivityResult(
//                contract = ActivityResultContracts.StartIntentSenderForResult(),
//                onResult = {
//                    result ->
//                    if(result.resultCode == RESULT_OK){
//                        lifecycleScope.launch{
//
//                        }
//                    }
//                })
//        }
//        composable(route = Screen.HomeScreen.route) {
//            home(navController = navController, sharedViewModel)
//        }
//        composable(
//            route = Screen.CarDetailScreen.route + "/{carModel}/{carBrand}",
//            arguments = listOf(
//                navArgument("carModel") {
//                    type = NavType.StringType
//                },
//                navArgument("carBrand") {
//                    type = NavType.StringType
//                },
//            )
//        ) { navBackStackEntry ->
//            carDetails(
//                carModel = navBackStackEntry.arguments?.getString("carModel"),
//                carBrand = navBackStackEntry.arguments?.getString("carBrand"),
//                navController,sharedViewModel)
//        }
//        composable(route = Screen.PaymentDetailScreen.route) {
//            payment(navController = navController)
//        }
//        composable(route = Screen.SearchScreen.route + "/{searchItem}",
//            arguments = listOf(
//                navArgument("searchItem") {
//                    type = NavType.StringType
//                }
//            )
//        ) {
//            navBackStackEntry ->
//            search(model = navBackStackEntry.arguments?.getString("searchItem"), navController, sharedViewModel)
//        }
//        composable(route = Screen.AllCarsScreen.route){
//            allCars(navController = navController, sharedViewModel)
//        }
//        composable(route = Screen.CartScreen.route){
//            cartScreen(navController)
//        }
//        composable(route = Screen.ProfileScreen.route){
//            profileScreen(navController)
//        }
//    }
//}