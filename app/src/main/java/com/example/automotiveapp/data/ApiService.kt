package com.example.automotiveapp.data

import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {
    @GET("brands")
    suspend fun getBrands(@Query("category") categoryId: Int): Response<BrandResponse>

}