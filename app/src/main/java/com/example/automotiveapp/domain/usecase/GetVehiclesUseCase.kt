package com.example.automotiveapp.domain.usecase

import com.example.automotiveapp.data.remote.Vehicle
import com.example.automotiveapp.domain.repository.Repository
import com.example.automotiveapp.utils.Resource
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetVehiclesUseCase @Inject constructor(
    private val repository: Repository
) {
    operator fun invoke(
        identificationAttributeId: Int,
        modelId: Int,
        identificationAttributeValueId: Int,
        category: Int
    ): Flow<Resource<List<Vehicle>>> {
        return repository.getVehicles(
            identificationAttributeId,
            modelId,
            identificationAttributeValueId,
            category
        )
    }
}