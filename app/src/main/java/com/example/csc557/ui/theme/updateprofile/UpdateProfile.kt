package com.example.csc557.ui.theme.updateprofile

import android.widget.Toast
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.NavController
import com.example.csc557.SharedViewModel
import com.example.csc557.ui.theme.model.AccountUser
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.runtime.*
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.text.input.KeyboardType

data class Object(
    var Title: String,
    var Input: MutableState<String>,
    val focusRequester: FocusRequester = FocusRequester(),
)

@Composable
fun updateProfile(
    googleUID: String?,
    navController: NavController,
    sharedViewModel: SharedViewModel
) {
    var context = LocalContext.current
//    val focusRequester = remember { FocusRequester() }
    val focusManager = LocalFocusManager.current

    //all mutableState
    var identityNumber = remember { mutableStateOf("") }
    var fullName = remember { mutableStateOf("") }
    var phoneNumber = remember { mutableStateOf("") }
    var licenseNumber = remember { mutableStateOf("") }

    sharedViewModel.retrieveSpecificUserData(googleUID!!) { AccountUser ->
        identityNumber.value = AccountUser.identityNumber
        fullName.value = AccountUser.fullName
        phoneNumber.value = AccountUser.phoneNumber
        licenseNumber.value = AccountUser.licenseNumber
    }

    val list = remember {
        mutableStateListOf(
            Object("Identity Number", identityNumber),
            Object("Full Name", fullName),
            Object("Phone Number", phoneNumber),
            Object("License Number", licenseNumber)
        )
    }

    Column(
        modifier = Modifier
            .fillMaxSize(),
    ) {
//        Text(text = googleUID.toString())
        Text(
            text = "Update Profile",
            modifier = Modifier
                .padding(start = 10.dp),
            fontSize = 30.sp,
        )
        Box(
            modifier = Modifier
                .fillMaxHeight(0.8f)
                .fillMaxWidth()
                .padding(horizontal = 10.dp)
        ) {
            Column(
                modifier = Modifier
                    .fillMaxSize(),
                verticalArrangement = Arrangement.SpaceAround
            ) {
//                var questionIndex by remember { mutableStateOf(0) }
//
//                val progress by animateFloatAsState(
//                    targetValue = (questionIndex / list.size.toFloat()),
//                )
//
//                LinearProgressIndicator(
//                    modifier = Modifier.fillMaxWidth(1f),
//                    progress = progress
//                )

                list.forEachIndexed { index, item ->
                    Text(item.Title, fontSize = 20.sp)
                    Card(
                        modifier = Modifier.padding(8.dp),
                        shape = RoundedCornerShape(25.dp),
                        elevation = 15.dp
                    ) {
                        TextField(
                            colors = TextFieldDefaults.textFieldColors(
                                textColor = Color.Black,
                                disabledTextColor = Color.Transparent,
                                backgroundColor = Color.White,
                                focusedIndicatorColor = Color.Transparent,
                                unfocusedIndicatorColor = Color.Transparent,
                                disabledIndicatorColor = Color.Transparent
                            ),
                            modifier = Modifier
                                .focusRequester(item.focusRequester)
                                .fillMaxWidth(),
                            value = item.Input.value,
                            onValueChange = { newText -> item.Input.value = newText },
                            placeholder = { Text("Enter your ${item.Title}") },
                            keyboardOptions = KeyboardOptions(
                                imeAction = if (index < list.lastIndex) ImeAction.Next else ImeAction.Done,
                                keyboardType = if (index == 0 || index == 2) KeyboardType.Number else KeyboardType.Text
                            ),
                            keyboardActions = KeyboardActions(
                                onNext = {
                                    list[0].focusRequester.requestFocus()
                                    if (index < list.lastIndex) {
                                        list[index + 1].focusRequester.requestFocus()
                                    }
//                                    questionIndex = index + 1
                                },
                                onDone = {
                                    focusManager.clearFocus()
//                                    questionIndex = index + 1
                                }
                            )
                        )
                    }
                }
            }

        }

        Button(
            modifier = Modifier
                .fillMaxWidth()
                .padding(30.dp)
                .height(70.dp),
            colors = ButtonDefaults.buttonColors(
                backgroundColor = Color(16, 85, 205)
            ),
            onClick = {
                if (googleUID != null) {

                    var account = AccountUser(
                        identityNumber = identityNumber.value,
                        fullName = fullName.value,
                        phoneNumber = phoneNumber.value,
                        licenseNumber = licenseNumber.value,
                        googleUID = googleUID,
                    )

                    sharedViewModel.saveUserData(
                        userUID = googleUID,
                        account = account,
                        context = context,
                    )
                }
                Toast.makeText(context, "Profile has been updated", Toast.LENGTH_LONG).show()
                navController.navigateUp()
            }
        ) {
            Text(text = "Update Profile", color = Color.White)
        }
    }
}