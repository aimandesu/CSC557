package com.example.csc557.ui.theme.cartscreen

import android.text.method.Touch
import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
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
                                .fillMaxHeight(0.92f)
                        ) {
                            itemsIndexed(theList) { index, item ->
                                productsCard(
                                    theList[index].carRent,
                                    theList[index].totalPrice.toString(),
                                    theList[index].image,
                                    theList,
                                    theList[index],
                                    sharedViewModel
                                )
                            }
                        }
                        Button(onClick = {}) {
                            Text(text = "TEst")
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
    theList: SnapshotStateList<Rent>,
    deleteRent: Rent,
    sharedViewModel: SharedViewModel
) {

    val context = LocalContext.current

    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(180.dp)
            .padding(10.dp),
        contentAlignment = Alignment.BottomCenter
    ) {
        Card(
            Modifier
                .fillMaxWidth()
                .height(150.dp),
            shape = RoundedCornerShape(
                topStart = 16.dp,
                bottomEnd = 16.dp,
                topEnd = 5.dp,
                bottomStart = 5.dp
            ),
            backgroundColor = Color.Blue,
            elevation = 0.dp
        ) {
            Row(
                modifier = Modifier
                    .matchParentSize()
                    .background(
                        Brush.linearGradient(
                            colors = listOf(
                                Color(16, 85, 205),
                                Color.White,
                            )
                        )
                    ),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxHeight()
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
                        text = price,
                        fontSize = 20.sp,
                        color = Color.Black
                    )
                    Button(onClick = {
                        //here fire the sharedviewmodel logic to delete
                        sharedViewModel.deleteRent(context, deleteRent.rentID)
                        Log.d("rent id", deleteRent.rentID)
                        theList.remove(deleteRent)
                    }) {
                        Text(text = "Delete")
                    }
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


    }
}