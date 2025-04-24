package com.example.automotiveapp.data

data class BrandResponse(
    val data: List<Brand>
)

data class Brand(
    val id: Int,
    val name: String,
    val image: String
)
