package com.example.csc557.ui.theme.home

import androidx.compose.foundation.*


//import androidx.compose.foundation.gestures.ModifierLocalScrollableContainerProvider.value
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.itemsIndexed

import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.material.MaterialTheme.colors
import androidx.compose.material.SnackbarDefaults.backgroundColor
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.text.style.TextDecoration

import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.compose.currentBackStackEntryAsState
import coil.compose.rememberImagePainter
import com.example.csc557.NavigationItem
import com.example.csc557.R
import com.example.csc557.Screen
import com.example.csc557.SharedViewModel
import com.example.csc557.ui.theme.bottomnavigation.bottomNavigation

//import com.example.csc557.ui.theme.data.carsAvailable
import com.example.csc557.ui.theme.model.Car

//import com.example.csc557.ui.theme.testing.bro
//import com.example.csc557.ui.theme.testing.testing

@Composable
fun home(navController: NavController, sharedViewModel: SharedViewModel) {
    val context = LocalContext.current
    val scrollState = rememberScrollState()
//    val navBackStackEntry by navController.currentBackStackEntryAsState()
//    val currentDestination = navBackStackEntry?.destination
    Box(
        modifier =
        Modifier
            .fillMaxHeight(1f)
            .fillMaxWidth(1f)
//            .background(brush = Brush.verticalGradient(listOf(Color.Red, Color.Blue)))
//            .background(Color.Green)
    ) {
        Scaffold(
            bottomBar = {
                NavigationItem(navController = navController)
            },
            content = { paddingValues ->
                Column(
                    modifier = Modifier
                        .verticalScroll(scrollState)
                        .padding(paddingValues)
                ) {
                    browseCars(navController)
                    viewAllCars(navController)
                    hotDeals(navController)
                    listCars(navController, sharedViewModel)
//                    Box(
//                        modifier = Modifier.fillMaxSize(),
//                        Alignment.BottomCenter
//                    ) {
//                        bottomNavigation()
//                    }
                }
            }
        )


    }
}

@Composable
fun browseCars(navController: NavController) {
    var text by remember { mutableStateOf("") }
    Column() {
        Text(
            text = "Home",
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
                    keyboardOptions = KeyboardOptions(
                        imeAction = ImeAction.Search
                    ),
                    keyboardActions = KeyboardActions(
                        onSearch = {
                            navController.navigate(Screen.SearchScreen.route + "/$text")
                        }
                    ),
                    decorationBox = { innerTextField ->
                        if (text.isEmpty()) {
                            Text(
                                text = "search model",
                                fontSize = 19.sp,
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
fun viewAllCars(navController: NavController) {
    Surface(
        shape = RoundedCornerShape(22.dp),
        modifier = Modifier
            .fillMaxWidth()
            .height(230.dp)
            .padding(10.dp)
    ) {
        Image(
            painter = rememberImagePainter("https://wallpaperaccess.com/full/40047.jpg"),
            contentDescription = "",
            contentScale = ContentScale.FillWidth,
        )
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(start = 10.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.Start
        ) {
            Text(
                text = "Welcome to Car Rental!",
                color = Color.White,
                modifier = Modifier
                    .background(
                        Brush.verticalGradient(
                            colors = listOf(Color.Transparent, Color.DarkGray),
                            startY = 10f
                        ),
                    ),
            )
            Spacer(modifier = Modifier.height(10.dp))
            Button(
                colors = ButtonDefaults.buttonColors(
                    backgroundColor = Color.Red
                ),
                onClick = {
                    navController.navigate(Screen.AllCarsScreen.route)
                }) {
                Text(text = "view all cars", color = Color.White)
            }
        }
    }

}

@Composable
fun hotDeals(navController: NavController) {
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
    }
}

@Composable
fun listCars(navController: NavController, sharedViewModel: SharedViewModel) {
//    val scrollState = rememberScrollState()
    val context = LocalContext.current
    val theList = sharedViewModel.fetchCarsHotDealsList()


    LazyRow(
        modifier =
        Modifier
//                .horizontalScroll(scrollState)
            .fillMaxWidth(1f)
            .height(300.dp)
            .padding(10.dp)
    ) {
        itemsIndexed(theList) { index, item ->

            if (theList[index].hotDeals) {
                Surface(
                    shape = RoundedCornerShape(22.dp),
                    modifier = Modifier.padding(5.dp),
//                    elevation = 15.dp
                ) {
                    Box(
                        Modifier

                            .fillMaxHeight()
                            .width(200.dp)
//                        .background(color = Color.Red)

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
                                    color = Color.White,
//                                    modifier = Modifier
//                                        .background(
//                                            Brush.verticalGradient(
//                                                colors = listOf(Color.Transparent, Color.DarkGray),
//                                                startY = 10f
//                                            ),
//                                        ),
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
                                        navController.navigate(Screen.CarDetailScreen.route + "/${theList[index].model}/${theList[index].brand}")
                                    }) {
                                    Text(text = "Rent Now", fontSize = 17.sp)
                                }
                            }
                        }
//                    }
                    }
                }
            }

        }

    }
}

