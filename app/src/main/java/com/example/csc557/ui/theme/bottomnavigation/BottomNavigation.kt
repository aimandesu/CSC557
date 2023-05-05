package com.example.csc557.ui.theme.bottomnavigation

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.AppBarDefaults
import androidx.compose.material.Icon
import androidx.compose.material.Surface
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountBox
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.Create
import androidx.compose.material.icons.filled.Delete
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.zIndex

@Composable
fun bottomNavigation() {
    Row(
        modifier =
        Modifier
            .fillMaxWidth()
            .shadow(AppBarDefaults.TopAppBarElevation)
            .zIndex(1f)
            .padding(25.dp),
//                .background(color = Color(16, 85, 205))

        horizontalArrangement = Arrangement.SpaceAround
    ) {
        Icon(imageVector = Icons.Default.Create, contentDescription = "")
        Icon(imageVector = Icons.Default.Delete, contentDescription = "")
        Icon(imageVector = Icons.Default.AccountCircle, contentDescription = "")
        Icon(imageVector = Icons.Default.AccountBox, contentDescription = "")
    }
}