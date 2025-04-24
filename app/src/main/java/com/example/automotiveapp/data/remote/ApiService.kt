package com.example.automotiveapp.data.remote

import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {
    @GET("brands")
    suspend fun getBrands(@Query("category") categoryId: Int): Response<BrandResponse>

    @GET("models")
    suspend fun getModels(
        @Query("page") page: Int,
        @Query("brand") brandId: Int,
        @Query("category") categoryId: Int
    ): Response<ModelResponse>

}