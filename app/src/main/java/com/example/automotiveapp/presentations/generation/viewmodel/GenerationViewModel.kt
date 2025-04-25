package com.example.automotiveapp.presentations.generation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.automotiveapp.data.remote.Vehicle
import com.example.automotiveapp.domain.usecase.GetVehiclesUseCase
import com.example.automotiveapp.utils.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class GenerationViewModel @Inject constructor(
    private val getVehiclesUseCase: GetVehiclesUseCase
) : ViewModel() {

    private val _vehicles = MutableStateFlow<Resource<List<Vehicle>>>(Resource.Loading())
    val vehicles: StateFlow<Resource<List<Vehicle>>> = _vehicles

    private val _compareList = MutableStateFlow<List<Vehicle>>(emptyList())
    val compareList: StateFlow<List<Vehicle>> = _compareList

    fun fetchVehicles(
        identificationAttributeId: Int,
        modelId: Int,
        identificationAttributeValueId: Int,
        category: Int
    ) {
        viewModelScope.launch {
            getVehiclesUseCase(
                identificationAttributeId,
                modelId,
                identificationAttributeValueId,
                category
            ).collect { resource ->
                _vehicles.value = resource
            }
        }
    }

    fun addToCompare(vehicle: Vehicle) {
        val currentList = _compareList.value.toMutableList()
        if (!currentList.contains(vehicle)) {
            currentList.add(vehicle)
            _compareList.value = currentList
        }
    }

    fun removeFromCompare(vehicle: Vehicle) {
        val currentList = _compareList.value.toMutableList()
        if (currentList.contains(vehicle)) {
            currentList.remove(vehicle)
            _compareList.value = currentList
        }
    }
}