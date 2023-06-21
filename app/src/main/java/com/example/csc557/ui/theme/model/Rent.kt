package com.example.csc557.ui.theme.model

data class Rent(
    var googleUID: String = "",
    var carRent: String = "",
    var startTime: String = "",
    var endTime: String = "",
    var totalPrice: Double = 0.0,
    var paid: Boolean = false,
    var image: String = "",
)
