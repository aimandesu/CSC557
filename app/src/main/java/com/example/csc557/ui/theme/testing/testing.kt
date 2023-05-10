package com.example.csc557.ui.theme.testing

import android.content.Context
import android.util.Log
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.navigation.NavController
import com.example.csc557.SharedViewModel
import androidx.compose.ui.platform.LocalContext
import com.example.csc557.R
import com.example.csc557.ui.theme.model.Car
import com.example.csc557.ui.theme.model.CarData
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue


@Composable
fun testing(navController: NavController, sharedViewModel: SharedViewModel) {
    var carsAvailable = ArrayList<Car>()

    var carModel: String by remember { mutableStateOf("") }
    var carBrand: String by remember { mutableStateOf("") }
    val carID: String by remember { mutableStateOf("") }

    val context = LocalContext.current
    sharedViewModel.retrieveSpecificUserData(context) { data ->
        Log.d("TAG", data.model)
        Log.d("TAG", carsAvailable.size.toString())
//        data.carModal
//        data.carId
//        data.carBrand
        carModel = data.model
        carBrand = data.brand

    }

            carsAvailable.add(
            Car(
                carBrand,
                carModel,
                10,
                10.00,
                R.drawable.sportcar,
                arrayListOf("3 seats", "up to ", "value", "auto", "lol"),
                true
            )
        )

    Log.d("TAG", carsAvailable.size.toString())
    for (i in carsAvailable){
        Text(text = i.model)
    }
}

@Composable fun bro(sharedViewModel: SharedViewModel){
    val context = LocalContext.current
    val theList = sharedViewModel.idk(context)
    LazyColumn{
        itemsIndexed(theList){
            index, item ->  
            Text(text = theList[index]?.brand + " selected..",)
            Text(text = theList[index]?.model + " selected..",)
            Text(text = theList[index]?.year.toString() + " selected..",)
            Text(text = theList[index]?.price.toString() + " selected..",)
            Text(text = theList[index]?.image + " selected..",)
            for(i in theList[index].carParts){
                Text(text = "$i selected..",)
            }
            Text(text = theList[index]?.hotDeals.toString() + " selected..",)
        }
    }
}