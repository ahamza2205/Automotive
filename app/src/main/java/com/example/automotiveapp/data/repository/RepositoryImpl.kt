package com.example.automotiveapp.data.repository

import com.example.automotiveapp.data.remote.ApiService
import com.example.automotiveapp.data.remote.Brand
import com.example.automotiveapp.domain.repository.Repository
import com.example.automotiveapp.utils.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.catch
import javax.inject.Inject

class RepositoryImpl @Inject constructor(
    private val apiService: ApiService
) : Repository {

    override fun getBrands(categoryId: Int): Flow<Resource<List<Brand>>> = flow {
        emit(Resource.Loading())

        val response = apiService.getBrands(categoryId)
        if (response.isSuccessful && response.body() != null) {
            emit(Resource.Success(response.body()!!.data))
        } else {
            emit(Resource.Error(response.message()))
        }

    }.catch { e ->
        emit(Resource.Error(e.localizedMessage ?: "An unexpected error occurred"))
    }
}

