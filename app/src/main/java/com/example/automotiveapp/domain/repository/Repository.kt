package com.example.automotiveapp.domain.repository

import com.example.automotiveapp.data.remote.Brand
import com.example.automotiveapp.data.remote.Model
import com.example.automotiveapp.utils.Resource
import kotlinx.coroutines.flow.Flow

interface Repository {
    fun getBrands(categoryId: Int): Flow<Resource<List<Brand>>>
    fun getModels(page: Int, brandId: Int, categoryId: Int): Flow<Resource<List<Model>>>

}