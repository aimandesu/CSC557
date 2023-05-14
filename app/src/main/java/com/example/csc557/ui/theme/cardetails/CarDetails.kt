package com.example.csc557.ui.theme.cardetails

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.MaterialTheme.colors
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material.icons.filled.Share
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import coil.compose.rememberImagePainter
import com.example.csc557.R
import com.example.csc557.Screen
import com.example.csc557.SharedViewModel
import com.example.csc557.ui.theme.bottomnavigation.bottomNavigation
import com.example.csc557.ui.theme.model.Car


@Composable
fun carDetails(
    carModel: String?,
    carBrand: String?,
    navController: NavController,
    sharedViewModel: SharedViewModel
) {


    var brand: String by remember { mutableStateOf("") }
    var model: String by remember { mutableStateOf("") }
    var price: Double by remember { mutableStateOf(0.00) }
    var year: Int by remember { mutableStateOf(0) }
    var carPart: ArrayList<String> by remember {
        mutableStateOf(ArrayList())
    }
    var image: String by remember { mutableStateOf("") }

    sharedViewModel.fetchSpecificCar(carModel.toString()) { carData ->
        brand = carData.brand
        model = carData.model
        price = carData.price
        year = carData.year
        carPart = carData.carParts
        image = carData.image
    }

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight(1f)
            .background(color = Color(16, 85, 205))

    ) {

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

            )

        carModel(carModel.toString())
        carImage(image)
        carParts(carPart)
        Box(
            modifier = Modifier.fillMaxSize(),
            Alignment.BottomCenter
        ) {
            rentCar(price, navController)
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
fun carImage(imageString: String) {

    Column(
        modifier = Modifier

            .fillMaxWidth()
            .height(250.dp)
            .background(color = Color.White),
        verticalArrangement = Arrangement.Center

    ) {
        Image(
            modifier = Modifier.fillMaxWidth(),
            painter = rememberImagePainter(imageString),
            contentDescription = "",
            contentScale = ContentScale.FillHeight,
            alignment = Alignment.Center,
        )

    }

}

@Composable
fun carParts(carParts: ArrayList<String>?) {
    LazyVerticalGrid(
//        modifier = Modifier.height(300.dp),
        columns = GridCells.Adaptive(minSize = 128.dp)
    ) {

        //find position for selected item in the array, the pass carParts.size
        items(carParts!!.size) { index ->
            PhotoItem(carParts, index)
        }
    }
}

@Composable
fun PhotoItem(carParts: ArrayList<String>?, index: Int) {
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
                text = carParts!![index],
                textAlign = TextAlign.Center,
                color = Color.White
            )

        }
    }

}

@Composable
fun rentCar(price: Double, navController: NavController) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight(0.4f),

        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            modifier = Modifier.width(200.dp),
            text = "RM$price/ Per Day",
            fontSize = 20.sp,
            color = Color.White,
            textAlign = TextAlign.Center,
        )
        Button(
            modifier = Modifier
                .width(200.dp)
                .fillMaxHeight(),
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


