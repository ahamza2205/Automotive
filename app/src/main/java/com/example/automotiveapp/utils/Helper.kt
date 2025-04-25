package com.example.automotiveapp.utils

fun formatPrice(price: Double): String {
    return String.format("%,d", price.toInt())
}
