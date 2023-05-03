package com.example.csc557.ui.theme.home

import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource

import androidx.compose.ui.unit.dp
import com.example.csc557.R

import com.example.csc557.ui.theme.data.carsAvailable
import com.example.csc557.ui.theme.model.Car

@Composable
fun home(){
    Box(modifier =
    Modifier
        .fillMaxHeight(1f)
        .fillMaxWidth(1f)
        .background(Color.Green)){
        listCars()
    }
}

@Composable
fun listCars(){
    val scrollState = rememberScrollState()
    Row(
        modifier =
        Modifier
            .horizontalScroll(scrollState)
            .fillMaxWidth(1f)
            .fillMaxHeight(0.4f)
            .padding(10.dp)
    ) {
        for (car in carsAvailable){
            Surface(
                shape = RoundedCornerShape(22.dp),
                modifier = Modifier.padding(5.dp) ) {
                Box(
                    Modifier

                        .fillMaxHeight()
                        .width(200.dp)
//                        .background(color = Color.Red)

                ) {
                    Image(painter = painterResource(id =  R.drawable.p1), contentDescription = "", contentScale = ContentScale.FillHeight, )
                    Column(modifier = Modifier.fillMaxSize(), verticalArrangement = Arrangement.SpaceBetween,) {
                        Row(
                            modifier =
                            Modifier
                                .padding(5.dp)
                                .fillMaxWidth()
                                .height(20.dp),
                            horizontalArrangement = Arrangement.SpaceBetween
                        ) {
                            Text(text = car.model)
                            Text(text = "RM" + car.price.toString())
                        }
                        Row(
                            modifier =
                            Modifier
                                .padding(5.dp)
                                .fillMaxWidth()
                                .height(20.dp),
                            horizontalArrangement = Arrangement.SpaceBetween
                        ) {
                            Text(text = "Details")
                            Button(
                                modifier =
                                Modifier.width(50.dp),
                                onClick = {}) {
                                Text(text = "Rent Now", )
                            }
                        }
                    }

                }
            }

        }

    }
}