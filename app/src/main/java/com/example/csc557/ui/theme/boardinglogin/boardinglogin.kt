package com.example.csc557.ui.theme.boardinglogin

import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.rememberImagePainter


@Composable
fun BoardingLogin(
    state: SignInState,
    onSignInClick: () -> Unit
) {
    val context = LocalContext.current
    
    LaunchedEffect(key1 = state.signInError,){
        state.signInError?.let {
            error ->
            Toast.makeText(
                context,
                error,
                Toast.LENGTH_LONG
            ).show()
        }
    }
    
    Box(modifier = Modifier
        .background(Color.Black)) {
        Image(
            painter = rememberImagePainter("https://wallpapers.com/images/hd/lamborghini-iphone-blue-car-on-road-lvlzcio603k6ldwq.jpg"),
            contentDescription = "",
            contentScale = ContentScale.FillBounds,
        )
        Column(
            modifier =
            Modifier
                .fillMaxSize(),
            verticalArrangement = Arrangement.SpaceBetween,
            horizontalAlignment = Alignment.Start
        ) {
            Text(
                text = "Premium \n Car Rental",
                fontSize = 45.sp,
                modifier = Modifier
                    .padding(top = 100.dp, start = 10.dp),
                fontWeight = FontWeight.Bold,
//                fontStyle = FontStyle.Italic,
                color = Color.White
            )
            Button(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(80.dp)
                    .padding(
                        horizontal = 20.dp,
                        vertical = 10.dp
                    ),
                colors = ButtonDefaults.buttonColors(
                    backgroundColor = Color.White
                ),
                shape = RoundedCornerShape(22.dp),
                onClick = {

                }) {
                Text(text = "Get Started", fontSize = 19.sp)
            }
        }
    }

}