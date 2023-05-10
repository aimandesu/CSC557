package com.example.csc557.ui.theme.allcars

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.csc557.Screen
import com.example.csc557.ui.theme.data.carsAvailable
import com.example.csc557.ui.theme.model.Car

@Composable
fun allCars(navController: NavController) {
    var listCarBrand: ArrayList<String> = ArrayList<String>();
    val scrollState = rememberScrollState()
    val scaffoldState = rememberScaffoldState()
    for (carBrand in carsAvailable) {
        if (!listCarBrand.contains(carBrand.brand)) {
            listCarBrand.add(carBrand.brand)
        }

    }
    Scaffold(
        Modifier.fillMaxSize(),
        scaffoldState = scaffoldState,
        topBar = {
            topBar(navController = navController)
        }, content = { paddingValues ->
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .fillMaxHeight(1f)
                    .verticalScroll(scrollState)
                    .background(color = Color(16, 85, 205))
                    .padding(paddingValues = paddingValues),
            ) {
                for (carBrand in listCarBrand) {
                    Text(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(start = 10.dp),
                        text = carBrand,
                        textAlign = TextAlign.Start,
                        fontSize = 35.sp,
                        color = Color.White)
                    for ((index, car) in carsAvailable.withIndex()) {
                        if (carBrand == car.brand) {
                            Surface(
                                shape = RoundedCornerShape(22.dp),
                                modifier = Modifier.padding(5.dp),
                                elevation = 15.dp
                            ) {
                                Box(
                                    Modifier
                                        .height(295.dp)
                                        .fillMaxWidth()
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
                    Spacer(
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(10.dp)
                    )
                }

            }
        }
    )


}

@Composable
fun topBar(navController: NavController) {
    TopAppBar(
        elevation = 0.dp,
        title = {
            Text("Models",  color = Color.White)
        },
        backgroundColor = Color(16, 85, 205),
        navigationIcon = {
            IconButton(onClick = {
                navController.navigateUp()
            }) {
                Icon(Icons.Filled.ArrowBack, null, tint = Color.White)
            }
        },
//            actions = {
//                IconButton(onClick = {/* Do Something*/ }) {
//                    Icon(Icons.Filled.Share, null)
//                }
//                IconButton(onClick = {/* Do Something*/ }) {
//                    Icon(Icons.Filled.Settings, null)
//                }
//            },
    )
}