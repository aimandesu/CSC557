package com.example.csc557.cartscreen

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import com.example.csc557.NavigationItem

@Composable
fun cartScreen(navController: NavController){
    Scaffold(
        bottomBar = {
            NavigationItem(navController = navController)
        },
        content = {
                paddingValues ->
            Column(modifier = Modifier.padding(paddingValues)) {
                Text(text = "cart screen")
            }
        }
    )
}