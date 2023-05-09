package com.example.csc557.ui.theme.data

import com.example.csc557.R
import com.example.csc557.ui.theme.model.Car


var carsAvailable = arrayOf(
    Car("Mercedes", "Benz", 5, 100.00, R.drawable.p1,
        arrayListOf("3 seats", "up to ", "value", "auto", "lol" ), true),
    Car("Ferrari", "Gita", 5, 100.00, R.drawable.musume,
        arrayListOf("5 seats", "up to data ", "value 12", "manual", "not wro" ), true),
    Car("Mercedes", "mor", 5, 100.00, R.drawable.nemesis,
        arrayListOf("3 seats", "up to ", "value", "auto", "value" ), true),
    Car("Mercedes", "gg", 5, 100.00, R.drawable.nemesis,
        arrayListOf("3 seats", "up to ", "value", "auto", "value" ), false),
)

//mapOf("seat" to "3 seats", "engine" to "up to ","radiator" to "value", "type" to "auto","clutch" to "value")