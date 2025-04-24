package com.example.automotiveapp.data.remote

data class BrandResponse(
    val data: List<Brand>
)

data class Brand(
    val id: Int,
    val name: String,
    val image: String
)
