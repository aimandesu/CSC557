package com.example.csc557.ui.theme.model

import android.media.ImageReader

class Car(brand: String, model: String, year: Int, price: Double, image: Int, carParts:ArrayList<String>, hotDeals: Boolean) {
        val brand: String = brand
        val model: String = model
        val year: Int = year
        val price: Double = price
        val image: Int = image
        val carParts: ArrayList<String> = carParts
        val hotDeals: Boolean = hotDeals
}