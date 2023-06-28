package com.example.csc557.ui.theme.cartscreen

import android.text.method.Touch
import android.util.Log
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.ArrowForward
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.rounded.ArrowForward
import androidx.compose.runtime.*
import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import coil.compose.rememberImagePainter
import com.airbnb.lottie.compose.*
import com.example.csc557.NavigationItem
import com.example.csc557.SharedViewModel
import com.example.csc557.ui.theme.boardinglogin.UserData
import com.example.csc557.ui.theme.model.Rent
import com.example.csc557.R as res

@Composable
fun cartScreen(
    navController: NavController,
    sharedViewModel: SharedViewModel,
    userData: UserData?
) {
    var resultNotFound: Boolean by remember { mutableStateOf(false) }

    Scaffold(
        bottomBar = {
            NavigationItem(navController = navController)
        },
        content = { paddingValues ->
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .fillMaxHeight(1f)
//                    .verticalScroll(scrollState)
//                    .background(color = Color(16, 85, 205))
                    .padding(paddingValues = paddingValues),
//                verticalArrangement = Arrangement.Center,
//                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                val theList = sharedViewModel.fetchRent(userData!!.userId) { boolResult ->
                    resultNotFound = boolResult

                }
                Text(
                    text = "Cart",
                    modifier = Modifier
                        .padding(start = 10.dp),
                    fontSize = 30.sp,
                )
                if (resultNotFound) {
                    val composition by rememberLottieComposition(
                        spec = LottieCompositionSpec.RawRes(
                            res.raw.empty
                        )
                    )
                    val progress by animateLottieCompositionAsState(
                        composition = composition,
                        iterations = LottieConstants.IterateForever
                    )

                    LottieAnimation(
                        modifier = Modifier.size(400.dp),
                        composition = composition,
                        progress = { progress },
                    )
                    Spacer(modifier = Modifier.height(20.dp))
                    Text(
                        modifier = Modifier.fillMaxWidth(),
                        text = "Cart is empty!",
                        textAlign = TextAlign.Center,
                        fontSize = 25.sp
                    )

                } else {
                    Column {
                        LazyColumn(
                            Modifier
                                .fillMaxHeight(0.9f)
                        ) {
                            itemsIndexed(theList) { index, item ->
                                productsCard(
                                    theList[index].carRent,
                                    theList[index].totalPrice.toString(),
                                    theList[index].image,
                                    theList[index].date,
                                    theList[index].startTime,
                                    theList[index].endTime,
                                    theList,
                                    theList[index],
                                    sharedViewModel
                                )
                            }
                        }
                        Button(
                            modifier = Modifier
                                .height(70.dp)
                                .fillMaxWidth()
                                .padding(vertical = 5.dp, horizontal = 5.dp),
                            colors = ButtonDefaults.buttonColors(
                                backgroundColor = Color(16, 85, 205)
                            ),
                            onClick = {

                            },
                        ) {
                            Text(text = "Pay Now", color = Color.White,  fontSize = 18.sp,)
                        }
                    }
                }
            }
        }
    )
}

@Composable
fun productsCard(
    title: String,
    price: String,
    carImage: String,
    date: String,
    startTime: String,
    endTime: String,
    theList: SnapshotStateList<Rent>,
    deleteRent: Rent,
    sharedViewModel: SharedViewModel
) {

    val context = LocalContext.current
    val expanded = remember { mutableStateOf(false) }
    val extraPadding = animateFloatAsState(
        targetValue = if (expanded.value) 0.98f else 1f,
        animationSpec = tween(durationMillis = 500)
    ).value



    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(200.dp)
            .padding(10.dp),
        contentAlignment = Alignment.BottomEnd
    ) {
        Row(
            Modifier
                .fillMaxWidth(extraPadding),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceAround
        ) {
            if (expanded.value) {
                IconButton(
                    modifier = Modifier
                        .height(30.dp)
                        .width(30.dp),
                    onClick = {
                        sharedViewModel.deleteRent(context, deleteRent.rentID)
                        theList.remove(deleteRent)
                    }) {
                    Icon(imageVector = Icons.Filled.Delete, contentDescription = null)
                }
            }
            Column {
                Card(
                    Modifier
                        .fillMaxWidth(extraPadding)
                        .height(150.dp),
                    shape = RoundedCornerShape(
                        topStart = 16.dp,
                        bottomEnd = 16.dp,
                        topEnd = 5.dp,
                        bottomStart = 5.dp
                    ),
//                    backgroundColor = Color.Blue,
                    elevation = 15.dp,
                ) {
                    Row(
                        modifier = Modifier
//                        .matchParentSize()
                            .fillMaxSize()
                            .background(
                                Brush.linearGradient(
                                    colors = listOf(
                                        Color(16, 85, 205),
                                        Color.White,
                                    )
                                )
                            ),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        IconButton(
                            modifier = Modifier
                                .height(30.dp)
                                .width(30.dp),
                            onClick = {
                                expanded.value = !expanded.value
                            },
                        ) {
                            if (expanded.value) {
                                Icon(
                                    imageVector = Icons.Filled.ArrowForward,
                                    contentDescription = null
                                )
                            } else {
                                Icon(
                                    imageVector = Icons.Filled.ArrowBack,
                                    contentDescription = null
                                )
                            }
                        }
                        Column(
                            modifier = Modifier
                                .fillMaxHeight()
                                .fillMaxWidth(0.5f)
                                .padding(start = 32.dp),
                            horizontalAlignment = Alignment.Start,
                            verticalArrangement = Arrangement.Center
                        ) {
                            Text(
                                text = title,
                                fontSize = 20.sp,
                                color = Color.Black
                            )
                            Spacer(modifier = Modifier.height(8.dp))
                            Text(
                                text = "RM$price",
                                fontSize = 20.sp,
                                color = Color.Black
                            )
                        }
                        Image(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(5.dp),
                            painter = rememberImagePainter(carImage),
                            contentDescription = "",
                            contentScale = ContentScale.FillHeight,
//                    alignment = Alignment.Center,
                        )
                    }
                }
                Surface(
                    modifier = Modifier
                        .height(70.dp)
                        .fillMaxWidth()
                        .padding(top = 5.dp),
                    shape = RoundedCornerShape(
                        topStart = 16.dp,
                        bottomEnd = 16.dp,
                        topEnd = 16.dp,
                        bottomStart = 16.dp
                    ),
                    elevation = 10.dp,
                ) {
                    Row(
                        modifier = Modifier


                            .background(
                                Brush.linearGradient(
                                    colors = listOf(
                                        Color(16, 85, 205),
                                        Color.White,
                                    )
                                )
                            ),
                    ) {
                        Text(
                            modifier = Modifier.padding(start = 5.dp),
                            text = date,
                            fontSize = 20.sp,
                            color = Color.Black
                        )
                        Spacer(Modifier.weight(1f))
                        Text(
                            text = startTime,
                            fontSize = 20.sp,
                            color = Color.Black
                        )
                        Text(
                            text = " | ",
                            fontSize = 20.sp,
                            color = Color.Black
                        )
                        Text(
                            modifier = Modifier.padding(end = 5.dp),
                            text = endTime,
                            fontSize = 20.sp,
                            color = Color.Black
                        )
                    }
                }

            }
        }
    }
}