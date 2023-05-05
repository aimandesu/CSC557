package com.example.csc557.ui.theme.home

import androidx.compose.foundation.*


//import androidx.compose.foundation.gestures.ModifierLocalScrollableContainerProvider.value
import androidx.compose.foundation.layout.*

import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
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
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.TextFieldValue

import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.csc557.R
import com.example.csc557.Screen
import com.example.csc557.ui.theme.bottomnavigation.bottomNavigation

import com.example.csc557.ui.theme.data.carsAvailable
import com.example.csc557.ui.theme.model.Car

@Composable
fun home(navController: NavController) {
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
            listCars(navController)
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
    var text by remember { mutableStateOf("") }
    Column() {
        Text(
            text = "Browse Cars",
            modifier = Modifier
                .padding(start = 10.dp),
            fontSize = 30.sp,
        )
        Surface(
            shape = RoundedCornerShape(22.dp),
            modifier = Modifier
                .fillMaxWidth()
                .height(70.dp)
//                .fillMaxHeight(0.12f)
                .padding(10.dp),
            elevation = 15.dp,
        ) {

            Box(modifier = Modifier.fillMaxSize()) {
                BasicTextField(
                    modifier = Modifier
                        .padding(start = 10.dp)
                        .align(Alignment.CenterStart),
                    value = text,
                    onValueChange = {
                        text = it
                    },
                    decorationBox = {
                            innerTextField ->
                        if (text.isEmpty()) {
                            Text(
                                text = "search",
                                fontSize = 18.sp,
                                fontWeight = FontWeight.Normal,
                                color = Color.LightGray
                            )
                        }
                        innerTextField()
                    }
                )
            }


        }

    }
}

@Composable
private fun MyUI(placeholder: String = "Enter Your Name") {
    var value by remember {
        mutableStateOf("")
    }

    BasicTextField(
        value = value,
        onValueChange = { newText ->
            value = newText
        },
        textStyle = TextStyle(
            fontSize = 20.sp,
            fontWeight = FontWeight.Medium,
            color = Color.DarkGray
        ),
        decorationBox = { innerTextField ->
            Box(
                modifier = Modifier
                    .padding(horizontal = 64.dp) // margin left and right
                    .fillMaxWidth()
                    .border(
                        width = 2.dp,
                        color = Color(0xFFAAE9E6),
                        shape = RoundedCornerShape(size = 16.dp)
                    )
                    .padding(horizontal = 16.dp, vertical = 12.dp), // inner padding
            ) {
                if (value.isEmpty()) {
                    Text(
                        text = placeholder,
                        fontSize = 18.sp,
                        fontWeight = FontWeight.Normal,
                        color = Color.LightGray
                    )
                }
                innerTextField()
            }
        }
    )
}

@Composable
fun hotDeals() {
    Row(
        modifier =
        Modifier
            .fillMaxWidth()
            .height(35.dp)
            .padding(horizontal = 10.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(text = "Hot deals", fontSize = 20.sp)
        TextButton(onClick = {}) {
            Text(text = "view all")
        }
    }
}

@Composable
fun listCars(navController: NavController) {
    val scrollState = rememberScrollState()
    Row(
        modifier =
        Modifier
            .horizontalScroll(scrollState)
            .fillMaxWidth(1f)
            .height(300.dp)
            .padding(10.dp)
    ) {
        for (car in carsAvailable) {
            Surface(
                shape = RoundedCornerShape(22.dp),
                modifier = Modifier.padding(5.dp),
                elevation = 15.dp
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
                                .height(50.dp),
                            horizontalArrangement = Arrangement.SpaceBetween
                        ) {
                            Text(text = car.model, fontSize = 20.sp)
                            Text(
                                text = "RM" + car.price.toString() + "\n/per day",
                                fontSize = 18.sp
                            )
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
                            Text(
                                text = "Details",
                                modifier = Modifier.padding(start = 5.dp),
                                fontSize = 18.sp
                            )
                            Button(
                                shape = RoundedCornerShape(topStart = 22.dp),
                                colors = ButtonDefaults.buttonColors(
                                    backgroundColor = Color.DarkGray,
                                    contentColor = Color.White
                                ),
                                onClick = {
                                    navController.navigate(Screen.CarDetailScreen.route + "/${car.model}")
                                }) {
                                Text(text = "Rent Now", fontSize = 17.sp)
                            }
                        }
                    }

                }
            }

        }

    }
}

