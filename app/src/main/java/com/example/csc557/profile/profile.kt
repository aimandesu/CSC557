package com.example.csc557.profile

import android.accounts.Account
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import coil.compose.rememberImagePainter
import com.example.csc557.NavigationItem
import com.example.csc557.SharedViewModel
import com.example.csc557.ui.theme.boardinglogin.UserData
import com.example.csc557.ui.theme.model.AccountUser

@Composable
fun profileScreen(
    navController: NavController,
    userData: UserData?,
    onSignOut: () -> Unit,
    sharedViewModel: SharedViewModel
) {
    var context = LocalContext.current
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

//                        if (userData != null) {
//
//                            //all except googleUID need to use mutableStateOf, googleUID need to pass to next screen
//                            var account = AccountUser(
//                                identityNumber = "",
//                                fullName = "",
//                                phoneNumber = "",
//                                licenseNumber = "",
//                                googleUID = "",
//                            )
//
//                            sharedViewModel.saveUserData(
//                                userUID = userData.userId,
//                                account = account,
//                                context = context,
//                            )
//                        }
                    },
                ) {
                    Text(text = "Update Profile")
                }
                Column(
                    modifier = Modifier
                        .fillMaxSize(),
                    verticalArrangement = Arrangement.SpaceAround
                ) {
                    Text(text = "Aiman Afiq")
                    Text(text = "011-3041")
                    Text(text = "01191")
                    Text(text = "01290931")
                }


            }
        }
    )

}