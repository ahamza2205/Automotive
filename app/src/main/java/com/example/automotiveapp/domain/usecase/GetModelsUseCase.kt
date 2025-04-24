package com.example.automotiveapp.domain.usecase

import com.example.automotiveapp.data.remote.Model
import com.example.automotiveapp.domain.repository.Repository
import com.example.automotiveapp.utils.Resource
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetModelsUseCase @Inject constructor(
    private val repository: Repository
) {
    operator fun invoke(page: Int, brandId: Int, categoryId: Int): Flow<Resource<List<Model>>> {
        return repository.getModels(page, brandId, categoryId)
    }
}