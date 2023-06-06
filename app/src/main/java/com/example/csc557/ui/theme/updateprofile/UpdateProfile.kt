package com.example.csc557.ui.theme.updateprofile

import android.widget.Toast
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Button
import androidx.compose.material.Text
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

@Composable
fun updateProfile(
    googleUID: String?,
    navController: NavController,
    sharedViewModel: SharedViewModel
) {
    var context = LocalContext.current

    //all mutableState
    var identityNumber: String by remember { mutableStateOf("") }
    var fullName: String by remember { mutableStateOf("") }
    var phoneNumber: String by remember { mutableStateOf("") }
    var licenseNumber: String by remember { mutableStateOf("") }

    Column(
        modifier = Modifier
            .fillMaxSize()
    ) {
        Text(text = googleUID.toString())
        Button(
            onClick = {
                if (googleUID != null) {

                    var account = AccountUser(
                        identityNumber = identityNumber,
                        fullName = fullName,
                        phoneNumber = phoneNumber,
                        licenseNumber = licenseNumber,
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
            Text(text = "Update Profile")
        }
    }
}