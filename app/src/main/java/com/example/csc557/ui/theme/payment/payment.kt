package com.example.csc557.ui.theme.payment

import android.app.DatePickerDialog
import android.app.TimePickerDialog
import android.util.Log
import android.widget.DatePicker
import android.widget.Toast
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.*
//import androidx.compose.material3.DatePicker
//import androidx.compose.material3.ExperimentalMaterial3Api
//import androidx.compose.material3.Text
//import androidx.compose.material3.rememberDatePickerState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.DialogProperties
import androidx.navigation.NavController
import com.example.csc557.Screen
import com.example.csc557.SharedViewModel
import com.example.csc557.ui.theme.components.customdialog.customDialog
import java.math.RoundingMode
import java.text.DateFormat
import java.text.DecimalFormat
import java.text.SimpleDateFormat
import java.util.*
import java.util.concurrent.TimeUnit
import com.example.csc557.ui.theme.boardinglogin.UserData
import com.example.csc557.ui.theme.model.Rent

@Composable
fun payment(
    navController: NavController,
    carModel: String?,
    carBrand: String?,
    price: String?,
    sharedViewModel: SharedViewModel,
    userData: UserData?
) {
    val mDate = remember { mutableStateOf("") }


    Column(
        modifier = Modifier
            .fillMaxHeight()
            .fillMaxSize()
    ) {
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
        calenderPreview(mDate)
        TimePreview(
            mDate,
            carBrand,
            carModel,
            price,
            navController,
            sharedViewModel,
            userData!!.userId
        )
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
fun calenderPreview(mDate: MutableState<String>) {
    val mContext = LocalContext.current
    val mYear: Int
    val mMonth: Int
    val mDay: Int
    val mCalendar = Calendar.getInstance()

    mYear = mCalendar.get(Calendar.YEAR)
    mMonth = mCalendar.get(Calendar.MONTH)
    mDay = mCalendar.get(Calendar.DAY_OF_MONTH)

    mCalendar.time = Date()


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
                .fillMaxWidth(0.9f),
            shape = RoundedCornerShape(22.dp),
            onClick = {
                mDatePickerDialog.show()
            }, colors = ButtonDefaults.buttonColors(backgroundColor = Color(16, 85, 205))
        ) {
            Text(text = "Select Rent Date", color = Color.White)
        }

    }

}


@Composable
fun TimePreview(
    mDate: MutableState<String>,
    carBrand: String?,
    carModel: String?,
    price: String?,
    navController: NavController,
    sharedViewModel: SharedViewModel,
    googleUID: String
) {
    val context = LocalContext.current

    var showDialog by remember {
        mutableStateOf(false)
    }

    var startTime by remember {
        mutableStateOf("")
    }

    var endTime by remember {
        mutableStateOf("")
    }

    if (showDialog) {
        customDialog(
            title = "Car is added to cart",
            message = "Go to cart?",
            onDismiss = { showDialog = false },
            onConfirm = {
                showDialog = false
                navController.navigate(Screen.CartScreen.route) {
                    popUpTo(navController.graph.id) {
                        inclusive = true
                    }
                }
            },
            onCancel = {
                showDialog = false
                navController.navigateUp()
            },
            properties = DialogProperties(
                dismissOnBackPress = false,
                dismissOnClickOutside = false
            )
        )
    }

    fun checkIfCanProceed(dateChoose: String, price: String) {
        if (dateChoose != "" && price != "") {
            var rent = Rent(
                googleUID = googleUID,
                carRent = "${carBrand.toString()} ${carModel.toString()}",
                startTime = startTime,
                endTime = endTime,
                totalPrice = price.toDouble()
            )
//            navController.navigate()
//            Toast.makeText(context, "$dateChoose $price", Toast.LENGTH_LONG).show()
            sharedViewModel.rentCar(rent)
            showDialog = true
        } else {
            Toast.makeText(context, "Some details is empty", Toast.LENGTH_LONG).show()
        }
    }

    var roundedPrice = ""

    val df: DateFormat = SimpleDateFormat("hh:mm:ss")

//    Log.d("Total Time", minutes.toString())

    val c = Calendar.getInstance()

    val startHour = c.get(Calendar.HOUR_OF_DAY)
    val startMinute = c.get(Calendar.MINUTE)

    val endHour = c.get(Calendar.HOUR_OF_DAY)
    val endMinute = c.get(Calendar.MINUTE)


    val startTimePicker = TimePickerDialog(
        context,
        { t, hourOfDay, minutes ->
            startTime = "$hourOfDay:$minutes:00"
        }, startHour, startMinute, false
    )

    val endTimePicker = TimePickerDialog(
        context,
        { t, hourOfDay, minutes ->
            endTime = "$hourOfDay:$minutes:00"
        }, endHour, endMinute, false
    )

    Column {
        Row(
            modifier = Modifier
                .fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceAround
        ) {
            Button(
                modifier = Modifier
                    .height(50.dp)
                    .width(180.dp),
                shape = RoundedCornerShape(22.dp),
                onClick = {
                    startTimePicker.show()
                },
                colors = ButtonDefaults.buttonColors(backgroundColor = Color(16, 85, 205))
            ) {
                Text(text = "Pick Start Hour", color = Color.White)
            }
            Button(
                modifier = Modifier
                    .height(50.dp)
                    .width(180.dp),
                shape = RoundedCornerShape(22.dp),
                onClick = {
                    endTimePicker.show()
                },
                colors = ButtonDefaults.buttonColors(backgroundColor = Color(16, 85, 205))
            ) {
                Text(text = "Pick End Hour", color = Color.White)
            }
        }


    }
    Column(
        modifier = Modifier
            .fillMaxHeight(0.9f)
            .fillMaxWidth()
            .padding(horizontal = 50.dp),
        verticalArrangement = Arrangement.SpaceAround,
    ) {

        Text(
            text = "Selected Car: ${carBrand.toString()} ${carModel.toString()}",
            fontSize = 20.sp,
            textAlign = TextAlign.Center
        )

        Text(
            text = "Price per hour: RM ${price.toString()}", fontSize = 20.sp,
            textAlign = TextAlign.Center
        )

        Text(
            text = "Selected Date: ${mDate.value}",
            fontSize = 20.sp,
            textAlign = TextAlign.Center
        )



        Text(
            text = "Start Time: $startTime", fontSize = 20.sp,
            textAlign = TextAlign.Center
        )



        Text(
            text = "End Time: $endTime", fontSize = 20.sp,
            textAlign = TextAlign.Center
        )


        if (startTime != "" && endTime != "") {
            var theTime = df.parse(endTime).time - df.parse(startTime).time
            var minutes = TimeUnit.MILLISECONDS.toMinutes(theTime)
            var price = (minutes / 60.00) * price.toString().toFloat()
//            var perHourPrice = price *
            val df = DecimalFormat("#.##")
            df.roundingMode = RoundingMode.DOWN
            roundedPrice = df.format(price)

            //if minutes and roundedPrice < 0 then remove time for startTime and endTime
            if (minutes < 0 && price < 0.00) {
                startTime = ""
                endTime = ""
                Toast.makeText(context, "Please select time correctly", Toast.LENGTH_LONG).show()
            } else {
                Text(
                    text = "The minute: $minutes", fontSize = 20.sp,
                    textAlign = TextAlign.Center
                )
                Text(
                    text = "The price: RM $roundedPrice", fontSize = 20.sp,
                    textAlign = TextAlign.Center
                )
            }
        }
    }
    Box(
        modifier = Modifier.fillMaxSize(),
        Alignment.BottomEnd
    ) {
        Button(
            modifier = Modifier
                .width(200.dp)
                .height(70.dp),
            shape = RoundedCornerShape(topStart = 22.dp),
            colors = ButtonDefaults.buttonColors(backgroundColor = Color(16, 85, 205)),
            onClick = {
                checkIfCanProceed(mDate.value, roundedPrice)
            }) {
            Text(text = "Add to Cart", color = Color.White)
        }
    }


}
