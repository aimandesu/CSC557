package com.example.csc557.ui.theme.model

import com.google.firebase.firestore.ServerTimestamp
import java.util.*

data class Product(
    var id: String? = null,
    var name: String? = null,
    var price: Int? = null,
    @ServerTimestamp
    var date: Date? = null
)
