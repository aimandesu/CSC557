package com.example.csc557.ui.theme.profile

import android.accounts.Account
import android.util.Log
import android.widget.Space
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import coil.compose.rememberImagePainter
import com.airbnb.lottie.compose.*
import com.example.csc557.NavigationItem
import com.example.csc557.Screen
import com.example.csc557.SharedViewModel
import com.example.csc557.ui.theme.boardinglogin.UserData
import com.example.csc557.ui.theme.model.AccountUser
import com.example.csc557.R as res

@Composable
fun profileScreen(
    navController: NavController,
    userData: UserData?,
    onSignOut: () -> Unit,
    sharedViewModel: SharedViewModel
) {

    var identityNumber: String by remember { mutableStateOf("") }
    var fullName: String by remember { mutableStateOf("") }
    var phoneNumber: String by remember { mutableStateOf("") }
    var licenseNumber: String by remember { mutableStateOf("") }

//    var account = AccountUser()

    if (userData != null) {
        sharedViewModel.retrieveSpecificUserData(userData.userId) { AccountUser ->
            identityNumber = AccountUser.identityNumber
            fullName = AccountUser.fullName
            phoneNumber = AccountUser.phoneNumber
            licenseNumber = AccountUser.licenseNumber
        }
//        Log.d("identitynum", identityNumber)


//        account = AccountUser(
//            identityNumber = identityNumber,
//            fullName = fullName,
//            phoneNumber = phoneNumber,
//            licenseNumber = licenseNumber,
//            googleUID = userData.userId,
//        )

    }

    Scaffold(
        bottomBar = {
            NavigationItem(navController = navController)
        },
        content = { paddingValues ->

            Column(
                modifier = Modifier
                    .padding(paddingValues)
            ) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = "Profile",
                        modifier = Modifier
                            .padding(start = 10.dp),
                        fontSize = 30.sp,
                    )
                    IconButton(onClick = onSignOut) {
                        Icon(imageVector = Icons.Filled.Close, contentDescription = "log out")
                    }
                }

                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(100.dp),
                    horizontalArrangement = Arrangement.SpaceAround,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    if (userData != null) {
                        if (userData.profilePictureUrl != null) {
                            Image(
                                modifier =
                                Modifier
                                    .height(80.dp)
                                    .width(80.dp)
                                    .clip(RoundedCornerShape(50.dp)),
                                painter = rememberImagePainter(userData.profilePictureUrl),
                                contentDescription = "",
//                                contentScale = ContentScale.FillWidth,
                            )

                        }
                    }
                    if (userData != null) {
                        if (userData.username != null) {
                            Text(
                                text = userData.username,
                                textAlign = TextAlign.Center,
                                fontSize = 25.sp
                            )
//                            Text(
//                                text = userData.userId,
//                                textAlign = TextAlign.Center,
//                                fontSize = 36.sp
//                            )

                        }
                    }
                }
                Spacer(modifier = Modifier.height(16.dp))
                Button(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(50.dp)
                        .padding(horizontal = 20.dp),
                    onClick = {
                        //navigate to screen for enter details, ni right now for testing
                        if (userData != null) {
                            navController.navigate(Screen.UpdateProfileScreen.route + "/${userData.userId}")
                        }
                    },
                ) {
                    Text(text = "Update Profile")
                }
                if (identityNumber != "") {
                    fourGrid(
                        identityNumber,
                        fullName,
                        phoneNumber,
                        licenseNumber,
                    )
                } else {

                    val composition by rememberLottieComposition(
                        spec = LottieCompositionSpec.RawRes(
                            res.raw.create_account
                        )
                    )

                    val progress by animateLottieCompositionAsState(
                        composition = composition,
                        iterations = LottieConstants.IterateForever
                    )

                    LottieAnimation(
                        modifier = Modifier
                            .size(300.dp)
                            .align(Alignment.CenterHorizontally),
                        composition = composition,
                        progress = { progress },
                    )

                    Text(
                        modifier = Modifier.fillMaxWidth(),
                        text = "Please Fill in your details before renting car.\n Thank You! ヽ(・∀・)ﾉ",
                        textAlign = TextAlign.Center,
                        fontSize = 25.sp
                    )
                }
//                Column(
//                    modifier = Modifier
//                        .fillMaxSize(),
//                    verticalArrangement = Arrangement.SpaceAround
//                ) {
//                    Text(text = "Aiman Afiq")
//                    Text(text = "011-3041")
//                    Text(text = "01191")
//                    Text(text = "01290931")
//                }

            }
        }
    )

}

@Composable
fun fourGrid(
    identityNumber: String,
    fullName: String,
    phoneNumber: String,
    licenseNumber: String,
) {
    LazyVerticalGrid(
        modifier = Modifier
            .fillMaxSize(),
        columns = GridCells.Fixed(2),
        verticalArrangement = Arrangement.SpaceAround
    ) {
        item {
            GridItem(identityNumber, "IC NO")
        }
        item {
            GridItem(fullName, "FULL NAME")
        }
        item {
            GridItem(phoneNumber, "PHONE NUMBER")
        }
        item {
            GridItem(licenseNumber, "LICENSE NUMBER")
        }
    }
}

@Composable
fun GridItem(item: String, title: String) {
    Surface(
        modifier = Modifier
            .height(200.dp)
            .padding(8.dp),
        shape = RoundedCornerShape(22.dp),
//        border = BorderStroke(1.dp, Color.Black),
        elevation = 30.dp,
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth(),
            contentAlignment = Alignment.Center
        ) {
            Column() {
                Text(text = title, fontSize = 18.sp)
                Spacer(modifier = Modifier.height(10.dp))
                Text(text = item, fontSize = 17.sp)
            }

        }
    }

}