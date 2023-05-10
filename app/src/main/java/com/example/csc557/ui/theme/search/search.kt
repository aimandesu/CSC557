package com.example.csc557.ui.theme.search

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
fun search(model: String?, navController: NavController) {
    val scrollState = rememberScrollState()
    val scaffoldState = rememberScaffoldState()
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
                verticalArrangement = Arrangement.Center
            ) {
                searchResult(searches = model.toString(), navController)
            }
        }
    )

}

@Composable
fun topBar(navController: NavController) {
    TopAppBar(
        elevation = 0.dp,
        title = {
            Text("Results",  color = Color.White)
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

@Composable
fun searchResult(searches: String, navController: NavController) {
//    val scrollState = rememberScrollState()
    var listToFind: ArrayList<Car> = ArrayList<Car>();
    var searchesTrim = searches.trim().lowercase().filter { !it.isWhitespace() }
    for ((index, value) in carsAvailable.withIndex()) {
//            println("the element at $index is $value")
        var modelTrimmed = value.model.trim().lowercase();

        var brandTrim = value.brand.trim().lowercase();
        if (modelTrimmed == searchesTrim ||
            brandTrim == searchesTrim ||
            brandTrim + modelTrimmed == searchesTrim
        ) {
            listToFind.add(value)
        }

    }

    if (listToFind.isEmpty()) {
        Text(
            modifier = Modifier
                .fillMaxWidth(),
            text = "Cars with the result: $searches is not found",
            textAlign = TextAlign.Center,
            fontSize = 20.sp,
            color = Color.White
        )
    } else {
//        Column(modifier = Modifier
//            .verticalScroll(scrollState)
//            .fillMaxHeight(1f)
//            .padding(10.dp)) {
//        }
        for (car in listToFind) {
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


}