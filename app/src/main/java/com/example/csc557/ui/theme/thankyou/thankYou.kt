package com.example.csc557.ui.theme.thankyou

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.paint
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.csc557.R

@Composable
fun thankYou(navController: NavController) {
    thanks()
    ButtonHome()


}

@Composable
fun thanks() {
    Box(
        modifier = with(Modifier) {
            Modifier
                .fillMaxSize()
                .paint(
                    painterResource(id = R.drawable.thanks),
                    contentScale = ContentScale.FillBounds
                )
        }
    ) {
    }
}

@Composable
fun ButtonHome() {
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.BottomCenter,
    ) {
        Surface(
            shape = RoundedCornerShape(22.dp),
//        border = BorderStroke(1.dp, Color.Black),
            elevation = 30.dp,
            modifier = Modifier
                .padding(vertical = 15.dp, horizontal = 20.dp)
                .fillMaxWidth()
                .height(50.dp),
        ) {
            Box(
                modifier = Modifier
                    .background(color = Color.DarkGray),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = "Home",
                    fontSize = 15.sp,
                    fontFamily = FontFamily.SansSerif,
                    color = Color.White
                )

            }
        }


    }
}