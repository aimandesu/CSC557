package com.example.csc557.ui.theme.cartpayment

import android.util.Log
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.csc557.SharedViewModel
import com.example.csc557.R
import com.example.csc557.Screen

@Composable
fun cartPayment(
    navController: NavController,
    sharedViewModel: SharedViewModel,
    myList: List<String>,
) {
    Column(
        modifier = Modifier.fillMaxSize(),
    ) {
        CreditCard()
        PayNow(navController)
    }
}

@Composable
fun CreditCard() {
    val scrollState = rememberScrollState()
    Row(
        modifier = Modifier
            .horizontalScroll(scrollState)
            .fillMaxWidth()
    ) {
        Column(
            modifier = Modifier
                .padding(50.dp)
                .background(color = Color.Blue)
                .width(300.dp)
                .fillMaxHeight(0.5f),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            Image(
                painter = painterResource(id = R.drawable.ccblackycc),
                contentDescription = null,
                contentScale = ContentScale.Crop
            )
        }

        Column(
            modifier = Modifier
                .padding(50.dp)
                .background(color = Color.Green)
                .width(300.dp)
                .fillMaxHeight(0.5f),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            Image(
                painter = painterResource(id = R.drawable.ccpurplycc),
                contentDescription = null,
                contentScale = ContentScale.Crop
            )
        }

        Column(
            modifier = Modifier
                .padding(50.dp)
                .background(color = Color.Green)
                .width(300.dp)
                .fillMaxHeight(0.5f),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            Image(
                painter = painterResource(id = R.drawable.ccgoldycc),
                contentDescription = null,
                contentScale = ContentScale.Crop
            )
        }
    }
}

@Composable
fun PayNow(navController: NavController) {
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.BottomCenter,
    ) {
        Surface(
            modifier = Modifier

                .padding(15.dp)
                .fillMaxWidth()
                .height(50.dp),
            shape = RoundedCornerShape(15.dp),
//        border = BorderStroke(1.dp, Color.Black),
            elevation = 30.dp,
        ) {
            Box(
                modifier = Modifier
                    .background(color = Color(16, 85, 205))
                    .clickable {
                        navController.navigate(Screen.ThankYou.route)

                    },
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = "Pay Now",
                    fontSize = 15.sp,
                    fontFamily = FontFamily.SansSerif,
                    color = Color.White
                )

            }
        }


    }
}