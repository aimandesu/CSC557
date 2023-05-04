package com.example.csc557.ui.theme.home

import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.MaterialTheme.colors
import androidx.compose.material.SnackbarDefaults.backgroundColor
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountBox
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.Create
import androidx.compose.material.icons.filled.Delete
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.TextFieldValue

import androidx.compose.ui.unit.dp
import com.example.csc557.R
import com.example.csc557.ui.theme.bottomnavigation.bottomNavigation

import com.example.csc557.ui.theme.data.carsAvailable
import com.example.csc557.ui.theme.model.Car

@Composable
fun home() {
    Box(
        modifier =
        Modifier
            .fillMaxHeight(1f)
            .fillMaxWidth(1f)
//            .background(Color.Green)
    ) {
        Column(
//            modifier = Modifier.background(Color.Red)
        ) {
            browseCars()
            hotDeals()
            listCars()
            Box(
                modifier = Modifier.fillMaxSize(),
                Alignment.BottomCenter
            ) {
                bottomNavigation()
            }
        }

    }
}

@Composable
fun browseCars() {
    var text by remember { mutableStateOf(TextFieldValue("")) }
    Column() {
        Text(text = "Browse Cars", modifier = Modifier.padding(start = 10.dp))
        OutlinedTextField(
            shape = RoundedCornerShape(22.dp),
            modifier = Modifier
                .fillMaxWidth()
//                .fillMaxHeight(0.12f)
                .padding(10.dp),
            value = text,
            label = { Text(text = "Enter Your Name") },
            onValueChange = {
                text = it
            }
        )
    }
}

@Composable
fun hotDeals() {
    Row(
        modifier =
        Modifier
            .fillMaxWidth()
            .fillMaxHeight(0.05f)
            .padding(horizontal = 10.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(text = "Hot deals")
        TextButton(onClick = {}) {
            Text(text = "view all")
        }
    }
}

@Composable
fun listCars() {
    val scrollState = rememberScrollState()
    Row(
        modifier =
        Modifier
            .horizontalScroll(scrollState)
            .fillMaxWidth(1f)
            .fillMaxHeight(0.5f)
            .padding(10.dp)
    ) {
        for (car in carsAvailable) {
            Surface(
                shape = RoundedCornerShape(22.dp),
                modifier = Modifier.padding(5.dp)
            ) {
                Box(
                    Modifier

                        .fillMaxHeight()
                        .width(200.dp)
//                        .background(color = Color.Red)

                ) {
                    Image(
                        painter = painterResource(id = car.image),
                        contentDescription = "",
                        contentScale = ContentScale.FillHeight,
                    )
                    Column(
                        modifier = Modifier.fillMaxSize(),
                        verticalArrangement = Arrangement.SpaceBetween,
                    ) {
                        Row(
                            modifier =
                            Modifier
                                .padding(5.dp)
                                .fillMaxWidth()
                                .height(20.dp),
                            horizontalArrangement = Arrangement.SpaceBetween
                        ) {
                            Text(text = car.model)
                            Text(text = "RM" + car.price.toString())
                        }
                        Row(
                            modifier =
                            Modifier
//                                .padding(5.dp)
                                .fillMaxWidth()
                                .height(35.dp),
                            horizontalArrangement = Arrangement.SpaceBetween,
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Text(text = "Details", modifier = Modifier.padding(start = 5.dp))
                            Button(
                                shape = RoundedCornerShape(topStart = 22.dp),
                                colors = ButtonDefaults.buttonColors(
                                    backgroundColor = Color.DarkGray,
                                    contentColor = Color.White
                                ),
                                onClick = {}) {
                                Text(text = "Rent Now")
                            }
                        }
                    }

                }
            }

        }

    }
}

