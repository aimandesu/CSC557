package com.example.csc557.ui.theme.search

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
import com.airbnb.lottie.compose.*
import com.example.csc557.R as res
import com.example.csc557.Screen
import com.example.csc557.SharedViewModel
import com.example.csc557.ui.theme.model.Car

@Composable
fun search(model: String?, navController: NavController, sharedViewModel: SharedViewModel) {
//    val scrollState = rememberScrollState()
    val scaffoldState = rememberScaffoldState()
    Scaffold(
        Modifier.fillMaxSize(),
        scaffoldState = scaffoldState,
        topBar = {
            topBar(navController = navController, searches = model.toString())
        }, content = { paddingValues ->
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .fillMaxHeight(1f)
//                    .verticalScroll(scrollState)
                    .background(color = Color(16, 85, 205))
                    .padding(paddingValues = paddingValues),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                searchResult(searches = model.toString(), navController, sharedViewModel)
            }
        }
    )

}

@Composable
fun topBar(navController: NavController, searches: String) {
    TopAppBar(
        elevation = 0.dp,
        title = {
            Text("Results: $searches", color = Color.White)
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
fun searchResult(searches: String, navController: NavController, sharedViewModel: SharedViewModel) {
//    val scrollState = rememberScrollState()
    var resultNotFound: Boolean by remember { mutableStateOf(false) }
    val theList = sharedViewModel.fetchCarSearches(searches.trim().lowercase()) { boolResult ->
        resultNotFound = boolResult
    }

    if (resultNotFound) {
        Column(
            modifier = Modifier
                .fillMaxSize()
        ) {
            val composition by rememberLottieComposition(
                spec = LottieCompositionSpec.RawRes(
                    res.raw.search_not_found
                )
            )

            val progress by animateLottieCompositionAsState(
                composition = composition,
                iterations = LottieConstants.IterateForever
            )

            LottieAnimation(
                modifier = Modifier
                    .size(300.dp)
                    .align(Alignment.CenterHorizontally),
                composition = composition,
                progress = { progress },
            )
            Text(
                modifier = Modifier
                    .fillMaxWidth(),
                text = "Cars with model name $searches is not found",
                textAlign = TextAlign.Center,
                fontSize = 20.sp,
                color = Color.White
            )
        }
    } else {
        LazyColumn(
        ) {
            itemsIndexed(theList) { index, item ->
                Surface(
                    shape = RoundedCornerShape(22.dp),
                    modifier = Modifier.padding(5.dp),
                    elevation = 15.dp
                ) {
                    Box(
                        Modifier
                            .height(390.dp)
                            .width(350.dp)
                            .fillMaxWidth()
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
                                    .padding(15.dp)
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
                                    .fillMaxWidth()
                                    .height(55.dp),
                                horizontalArrangement = Arrangement.SpaceBetween,
                                verticalAlignment = Alignment.CenterVertically
                            ) {
                                Text(
                                    text = "Details",
                                    modifier = Modifier.padding(start = 15.dp),
                                    fontSize = 18.sp,
                                    color = Color.White
                                )
                                Button(
                                    modifier = Modifier
                                        .fillMaxHeight()
                                        .width(150.dp),
                                    shape = RoundedCornerShape(topStart = 22.dp),
                                    colors = ButtonDefaults.buttonColors(
                                        backgroundColor = Color.DarkGray,
                                        contentColor = Color.White
                                    ),
                                    onClick = {
                                        navController.navigate(Screen.CarDetailScreen.route + "/${theList[index].model}/${theList[index].brand}")
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
}