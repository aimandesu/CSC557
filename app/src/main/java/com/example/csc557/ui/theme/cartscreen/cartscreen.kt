package com.example.csc557.ui.theme.cartscreen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.airbnb.lottie.compose.*
import com.example.csc557.NavigationItem
import com.example.csc557.SharedViewModel
import com.example.csc557.ui.theme.boardinglogin.UserData
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
                    LazyColumn {
                        itemsIndexed(theList) { index, item ->
                            Column() {
                                Text(
                                    text = theList[index].startTime,
                                    fontSize = 20.sp,
                                    color = Color.Black
                                )
                            }
                        }
                    }
                }
            }
        }
    )
}