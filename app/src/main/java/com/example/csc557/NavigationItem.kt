package com.example.csc557

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.*
import androidx.compose.material.SnackbarDefaults.backgroundColor
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountBox
import androidx.compose.material.icons.filled.Face
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.zIndex
import androidx.navigation.NavController
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.compose.currentBackStackEntryAsState

@Composable
fun NavigationItem(navController: NavController) {
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentDestination = navBackStackEntry?.destination
    Box(
        modifier = Modifier
//            .padding(top = 10.dp)
            .background(color = Color.Black)
    ) {
        BottomNavigation(
            elevation = 40.dp,
        modifier =
        Modifier
            .padding(top = 2.dp),
////            .fillMaxWidth()
//            .shadow(AppBarDefaults.TopAppBarElevation)
//            .zIndex(1f),
////            .padding(25.dp),
            backgroundColor = Color.White
        ) {
            BottomNavigationItem(
                selected = currentDestination?.hierarchy?.any {
                    it.route == Screen.HomeScreen.route
                } == true,
                label = {
                    Text(text = "home")
                },
                icon = {
                    Icon(
                        imageVector = Icons.Filled.Home,
                        contentDescription = "Navigation Icon"
                    )
                },
                onClick = {
                    navController.navigate(Screen.HomeScreen.route)
                },
            )
            BottomNavigationItem(
                selected = currentDestination?.hierarchy?.any {
                    it.route == Screen.CartScreen.route
                } == true,
                label = {
                    Text(text = "cart")
                },
                icon = {
                    Icon(
                        imageVector = Icons.Filled.ShoppingCart,
                        contentDescription = "Navigation Icon"
                    )
                },
                onClick = {
                    navController.navigate(Screen.CartScreen.route)
                },
            )
            BottomNavigationItem(
                selected = currentDestination?.hierarchy?.any {
                    it.route == Screen.ProfileScreen.route
                } == true,
                label = {
                    Text(text = "profile")
                },
                icon = {
                    Icon(
                        imageVector = Icons.Filled.AccountBox,
                        contentDescription = "Navigation Icon"
                    )
                },
                onClick = {
                    navController.navigate(Screen.ProfileScreen.route)
                },
            )
        }
    }

}