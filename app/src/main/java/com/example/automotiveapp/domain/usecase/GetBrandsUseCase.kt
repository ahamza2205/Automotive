package com.example.automotiveapp.domain.usecase

import com.example.automotiveapp.data.remote.Brand
import com.example.automotiveapp.domain.repository.Repository
import com.example.automotiveapp.utils.Resource
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetBrandsUseCase @Inject constructor(
    private val repository: Repository
) {
    operator fun invoke(categoryId: Int): Flow<Resource<List<Brand>>> {
        return repository.getBrands(categoryId)
    }
}
