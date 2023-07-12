package com.example.csc557.ui.theme.pastorder

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.airbnb.lottie.compose.*
import com.example.csc557.R
import com.example.csc557.SharedViewModel
import com.example.csc557.ui.theme.boardinglogin.UserData

@Composable
fun PastOrder(
    googleUID: String?,
    navController: NavController,
    sharedViewModel: SharedViewModel
) {
    var resultNotFound: Boolean by remember { mutableStateOf(false) }

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight()
    ) {
        val theList = sharedViewModel
            .fetchRent(googleUID.toString(), true)
            { boolResult ->
                resultNotFound = boolResult
            }
        Text(
            text = "Past Order",
            modifier = Modifier
                .padding(start = 10.dp),
            fontSize = 30.sp,
        )
        if (resultNotFound) {
            val composition by rememberLottieComposition(
                spec = LottieCompositionSpec.RawRes(
                    R.raw.list_empty
                )
            )
//            val progress by animateLottieCompositionAsState(
//                composition = composition,
//                iterations = LottieConstants.IterateForever
//            )

            LottieAnimation(
                modifier = Modifier.size(400.dp),
                composition = composition,
//                progress = { progress },
            )
            Spacer(modifier = Modifier.height(20.dp))
            Text(
                modifier = Modifier.fillMaxWidth(),
                text = "No record found. (´･ᴗ･ ` )",
                textAlign = TextAlign.Center,
                fontSize = 25.sp
            )
        } else {
            LazyColumn(
                Modifier
                    .fillMaxHeight(0.9f)
            ) {
                itemsIndexed(theList) { index, item ->
                    pastOrder(
                        carRent = theList[index].carRent,
                        price = theList[index].totalPrice.toString(),
                        date = theList[index].date,
                        startTime = theList[index].startTime,
                        endTime = theList[index].endTime
                    )
                }
            }
        }
    }


}

@Composable
fun pastOrder(
    carRent: String,
    price: String,
    date: String,
    startTime: String,
    endTime: String,
) {
    Surface(
        modifier = Modifier
            .height(100.dp)
            .fillMaxWidth()
            .padding(10.dp),
        shape = RoundedCornerShape(
            topStart = 16.dp,
            bottomEnd = 16.dp,
            topEnd = 16.dp,
            bottomStart = 16.dp
        ),
        elevation = 10.dp
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(Color(16, 85, 205))
                .padding(5.dp),
            verticalArrangement = Arrangement.SpaceBetween
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(
                    text = carRent,
                    fontSize = 20.sp,
                    color = Color.White,
                )
                Text(
                    text = "RM$price",
                    fontSize = 20.sp,
                    color = Color.White,
                )
            }
            Row(
                modifier = Modifier
                    .fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(
                    text = date,
                    fontSize = 20.sp,
                    color = Color.White,
                )
                Row {
                    Text(
                        text = startTime,
                        fontSize = 20.sp,
                        color = Color.White,
                    )
                    Text(
                        text = " | ",
                        fontSize = 20.sp,
                        color = Color.White,
                    )
                    Text(
                        text = endTime,
                        fontSize = 20.sp,
                        color = Color.White,
                    )
                }
            }
        }
    }
}