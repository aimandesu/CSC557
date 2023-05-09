package com.example.csc557.ui.theme.payment

import android.app.DatePickerDialog
import android.widget.DatePicker
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
//import androidx.compose.material3.DatePicker
//import androidx.compose.material3.ExperimentalMaterial3Api
//import androidx.compose.material3.Text
//import androidx.compose.material3.rememberDatePickerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import java.util.*

@Composable
fun payment(navController: NavController) {
    Column() {
        TopAppBar(
            title = { Text(text = "Payment & Details") },
            backgroundColor = Color.White,
            navigationIcon = {

                IconButton(onClick = {
                    navController.navigateUp()
                }) {
                    Icon(
                        imageVector = Icons.Default.ArrowBack,
                        contentDescription = " "
                    )
                }
            }
        )
        calenderPreview()
    }
}

//@OptIn(ExperimentalMaterial3Api::class)
//@Composable
//fun calenderPreview(){
//    Column(verticalArrangement = Arrangement.spacedBy(8.dp)) {
//        // Pre-select a date for January 4, 2020
//        val datePickerState = rememberDatePickerState(initialSelectedDateMillis = 1578096000000)
////        DatePicker(state = )
//        DatePicker(state = datePickerState, modifier = Modifier.padding(16.dp))
//
//
//        Text("Selected date timestamp: ${datePickerState.selectedDateMillis ?: "no selection"}")
//    }
//
//
//}

@Composable
fun calenderPreview() {
    val mContext = LocalContext.current
    val mYear: Int
    val mMonth: Int
    val mDay: Int
    val mCalendar = Calendar.getInstance()

    mYear = mCalendar.get(Calendar.YEAR)
    mMonth = mCalendar.get(Calendar.MONTH)
    mDay = mCalendar.get(Calendar.DAY_OF_MONTH)

    mCalendar.time = Date()
    val mDate = remember { mutableStateOf("") }


    val mDatePickerDialog = DatePickerDialog(
        mContext,
        { _: DatePicker, mYear: Int, mMonth: Int, mDayOfMonth: Int ->
            mDate.value = "$mDayOfMonth/${mMonth + 1}/$mYear"
        }, mYear, mMonth, mDay
    )

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight(0.2f),
        verticalArrangement = Arrangement.SpaceAround,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        // Creating a button that on
        // click displays/shows the DatePickerDialog
        Button(
            modifier = Modifier
                .height(50.dp)
                .width(280.dp),
            shape = RoundedCornerShape(22.dp),
            onClick = {
                mDatePickerDialog.show()
            }, colors = ButtonDefaults.buttonColors(backgroundColor = Color(16, 85, 205))
        ) {
            Text(text = "Select Rent Date", color = Color.White)
        }

        // Displaying the mDate value in the Text
        Text(text = "Selected Date: ${mDate.value}", fontSize = 20.sp, textAlign = TextAlign.Center)

    }

}

@Composable
fun TimePreview(){

}
