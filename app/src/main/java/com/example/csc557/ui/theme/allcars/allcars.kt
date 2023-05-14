package com.example.csc557.ui.theme.allcars

import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import coil.compose.rememberImagePainter
import com.example.csc557.Screen
import com.example.csc557.SharedViewModel
import com.example.csc557.ui.theme.model.Car
import com.example.csc557.ui.theme.search.topBar
import kotlinx.coroutines.delay

//import com.example.csc557.ui.theme.search.topBar

@Composable
fun allCars(navController: NavController, sharedViewModel: SharedViewModel) {
    var carsBrand: ArrayList<String> by remember { mutableStateOf(ArrayList()) }
    val theList = sharedViewModel.fetchAllCars { arrayList ->
        carsBrand = arrayList
    }
//    val theList = sharedViewModel.fetchCarsHotDealsList()

//    var listCarBrand: ArrayList<String> = ArrayList<String>();
//    var brands: ArrayList<String> = ArrayList<String>();
//    var brand: String by remember { mutableStateOf("") }
    val scrollState = rememberScrollState()
    val scaffoldState = rememberScaffoldState()
    var show by remember { mutableStateOf(false) }
//    for (carBrand in carsAvailable) {
//        if (!listCarBrand.contains(carBrand.brand)) {
//            listCarBrand.add(carBrand.brand)
//        }
//
//    }
    Scaffold(
        Modifier.fillMaxSize(),
        scaffoldState = scaffoldState,
        topBar = {
//            topBar(navController = navController, show)
            TopAppBar(
                elevation = 0.dp,
                title = {
                    Text("Models", color = Color.White)
                },
                backgroundColor = Color(16, 85, 205),
                navigationIcon = {
                    IconButton(onClick = {
                        navController.navigateUp()
                        show = false
                    }) {
                        Icon(Icons.Filled.ArrowBack, null, tint = Color.White)
                    }
                },
            )
        }, content = { paddingValues ->
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .fillMaxHeight(1f)
                    .verticalScroll(scrollState)
                    .background(color = Color(16, 85, 205))
                    .padding(paddingValues = paddingValues),
            ) {
//                Button(onClick = {
//                    sharedViewModel.fetchAllCars {
//                        arrayList ->
//                        carsBrand = arrayList
//                    }
//                }) {
//                    Text(text = "tekan")
//                }
                LaunchedEffect(key1 = Unit) {
                    delay(800)
                    show = true
                }
                if (show) {
                    for (carBrand in carsBrand) { //toyota, honda
                        Text(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(start = 10.dp),
                            text = carBrand,
                            textAlign = TextAlign.Start,
                            fontSize = 35.sp,
                            color = Color.White
                        )
//                    for ((index, car) in carsAvailable.withIndex()) {
                        LazyRow {
                            itemsIndexed(theList) { index, item ->
                                if (carBrand == theList[index].brand) {
                                    Surface(
                                        shape = RoundedCornerShape(22.dp),
                                        modifier = Modifier.padding(5.dp),
                                        elevation = 15.dp
                                    ) {
                                        Box(
                                            Modifier
                                                .height(295.dp)
                                                .width(295.dp)
                                        ) {
                                            Image(
                                                painter = rememberImagePainter(theList[index].image),
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
                                                    Text(
                                                        text = theList[index].model,
                                                        fontSize = 20.sp,
                                                        color = Color.White
                                                    )
                                                    Text(
                                                        text = "RM" + theList[index].price.toString() + "\n/per day",
                                                        fontSize = 18.sp,
                                                        color = Color.White
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
                                                        fontSize = 18.sp,
                                                        color = Color.White
                                                    )
                                                    Button(
                                                        shape = RoundedCornerShape(topStart = 22.dp),
                                                        colors = ButtonDefaults.buttonColors(
                                                            backgroundColor = Color.DarkGray,
                                                            contentColor = Color.White
                                                        ),
                                                        onClick = {
                                                            show = false
                                                            navController.navigate(Screen.CarDetailScreen.route + "/${theList[index].model}/${theList[index].brand}")
                                                        }) {
                                                        Text(text = "Rent Now", fontSize = 17.sp)
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
                }
            }
        }
    )


}

//@Composable
//fun topBar(navController: NavController, show: Boolean) {
//    TopAppBar(
//        elevation = 0.dp,
//        title = {
//            Text("Models", color = Color.White)
//        },
//        backgroundColor = Color(16, 85, 205),
//        navigationIcon = {
//            IconButton(onClick = {
//                navController.navigateUp()
//                show = false
//            }) {
//                Icon(Icons.Filled.ArrowBack, null, tint = Color.White)
//            }
//        },
////            actions = {
////                IconButton(onClick = {/* Do Something*/ }) {
////                    Icon(Icons.Filled.Share, null)
////                }
////                IconButton(onClick = {/* Do Something*/ }) {
////                    Icon(Icons.Filled.Settings, null)
////                }
////            },
//    )
//}