package com.example.automotiveapp.domain

import com.example.automotiveapp.data.remote.Brand
import com.example.automotiveapp.utils.Resource
import kotlinx.coroutines.flow.Flow

interface Repository {
    fun getBrands(categoryId: Int): Flow<Resource<List<Brand>>>

}