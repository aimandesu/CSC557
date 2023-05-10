package com.example.csc557.ui.theme.cardetails

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.MaterialTheme.colors
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material.icons.filled.Share
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.csc557.Screen
import com.example.csc557.ui.theme.bottomnavigation.bottomNavigation
import com.example.csc557.ui.theme.data.carsAvailable


@Composable
fun carDetails(carModel: String?, carBrand: String? , navController: NavController) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight(1f)
            .background(color = Color(16, 85, 205))
    ) {
        var indexToFind: Int = 0;
        for ((index, value) in carsAvailable.withIndex()) {
//            println("the element at $index is $value")
            if (value.model == carModel) {
                indexToFind = index;
            }
        }
        TopAppBar(
            elevation = 0.dp,
            title = {
                Text(carBrand.toString(), color = Color.White)
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
        carModel(carModel.toString())
        carImage(indexToFind)
        carParts(indexToFind)
        Box(
            modifier = Modifier.fillMaxSize(),
            Alignment.BottomCenter
        ) {
            rentCar(indexToFind, navController)
        }

    }
}

@Composable
fun carModel(model: String) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(50.dp)
            .padding(start = 15.dp)
    ) {
        Text(text = model, fontSize = 30.sp, color = Color.White)
    }
}

@Composable
fun carImage(indexToFind: Int) {

    Column(
        modifier = Modifier

            .fillMaxWidth()
            .height(250.dp)
            .background(color = Color.White),
        verticalArrangement = Arrangement.Center

    ) {
        Image(
            modifier = Modifier.fillMaxWidth(),
            painter = painterResource(id = carsAvailable[indexToFind].image),
            contentDescription = "",
            contentScale = ContentScale.FillHeight,
            alignment = Alignment.Center,
        )

    }

}

@Composable
fun carParts(indexToFind: Int) {
    LazyVerticalGrid(
        columns = GridCells.Adaptive(minSize = 128.dp)
    ) {

        //find position for selected item in the array, the pass carParts.size
        items(carsAvailable[indexToFind].carParts.size) { photo ->
            PhotoItem(indexToFind, photo)
        }
    }
}

@Composable
fun PhotoItem(indexToFind: Int, photo: Int) {
    Surface(
        border = BorderStroke(1.dp, color = Color.White),
        shape = RoundedCornerShape(22.dp),
        modifier = Modifier
            .padding(10.dp)

    ) {
        Column(
            modifier = Modifier

                .width(118.dp)
                .height(105.dp)
                .background(color = Color(16, 85, 205)),
            verticalArrangement = Arrangement.Center

        ) {
            Text(
                modifier = Modifier.fillMaxWidth(),
                text = carsAvailable[indexToFind].carParts[photo],
                textAlign = TextAlign.Center,
                color = Color.White
            )

        }
    }

}

@Composable
fun rentCar(indexToFind: Int, navController: NavController) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight(0.4f),

        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            modifier = Modifier.width(200.dp),
            text = "RM" + carsAvailable[indexToFind].price.toString() + "/ Per Day",
            fontSize = 20.sp,
            color = Color.White,
            textAlign = TextAlign.Center,
        )
        Button(
            modifier = Modifier.width(200.dp).fillMaxHeight(),
            shape = RoundedCornerShape(topStart = 22.dp),
            colors = ButtonDefaults.buttonColors(
                backgroundColor = Color.DarkGray,
                contentColor = Color.White
            ),
            onClick = {
                navController.navigate(Screen.PaymentDetailScreen.route)
            }) {
            Text(text = "Rent Now")
        }
    }
}


