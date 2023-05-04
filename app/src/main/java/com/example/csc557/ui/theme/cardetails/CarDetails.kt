package com.example.csc557.ui.theme.cardetails

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.MaterialTheme.colors
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.example.csc557.ui.theme.data.carsAvailable


@Composable
fun carDetails() {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight(1f)
            .background(color = Color.Blue)
    ) {
        var indexToFind: Int = 0;
        for ((index, value) in carsAvailable.withIndex()) {
//            println("the element at $index is $value")
            if (value.model == "Gita") {
                indexToFind = index;
            }
        }
        TopAppBar(
            modifier = Modifier
                .fillMaxWidth()
                .height(50.dp)
        ) {

        }
        carImage(indexToFind)
        carParts(indexToFind)
        rentCar(indexToFind)
    }
}

@Composable
fun carImage(indexToFind: Int) {

    Column(
        modifier = Modifier

            .fillMaxWidth()
            .fillMaxHeight(0.3f)
            .background(color = Color.White),
        verticalArrangement = Arrangement.Center

    ) {
        Image(
            modifier = Modifier.fillMaxWidth(),
            painter = painterResource(id = carsAvailable[indexToFind].image),
            contentDescription = "",
            contentScale = ContentScale.FillWidth,
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
        shape = RoundedCornerShape(22.dp),
        modifier = Modifier.padding(10.dp)
    ) {
        Column(
            modifier = Modifier

                .width(118.dp)
                .height(118.dp)
                .background(color = Color.White),
            verticalArrangement = Arrangement.Center

        ) {
            Text(
                modifier = Modifier.fillMaxWidth(),
                text = carsAvailable[indexToFind].carParts[photo],
                textAlign = TextAlign.Center,
            )

        }
    }

}

@Composable
fun rentCar(indexToFind: Int) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight(0.2f),
        horizontalArrangement = Arrangement.SpaceAround,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(text = carsAvailable[indexToFind].price.toString())
        Button(
            modifier = Modifier.fillMaxWidth(0.6f),
            shape = RoundedCornerShape(15.dp),
            colors = ButtonDefaults.buttonColors(
                backgroundColor = Color.DarkGray,
                contentColor = Color.White
            ),
            onClick = {}) {
            Text(text = "Rent Now")
        }
    }
}


