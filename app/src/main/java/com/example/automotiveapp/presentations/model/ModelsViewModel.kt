package com.example.automotiveapp.presentations.model

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.automotiveapp.data.remote.Model
import com.example.automotiveapp.domain.usecase.GetModelsUseCase
import com.example.automotiveapp.utils.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ModelsViewModel @Inject constructor(
    private val getModelsUseCase: GetModelsUseCase,
) : ViewModel() {

    private val _viewType = MutableStateFlow(ViewType.Grid)
    val viewType: StateFlow<ViewType> = _viewType

    private val _models = MutableStateFlow<List<Model>>(emptyList())
    val models: StateFlow<List<Model>> = _models

    private val page = MutableStateFlow(1)
    private val isLoading = MutableStateFlow(false)
    private val hasMore = MutableStateFlow(true)


    fun toggleViewType() {
        _viewType.value = when (_viewType.value) {
            ViewType.List -> ViewType.Grid
            ViewType.Grid -> ViewType.List
        }
    }

    fun fetchModels(brandId : Int) {
        if (isLoading.value || !hasMore.value) return
        viewModelScope.launch {
            isLoading.value = true
            getModelsUseCase(page.value, brandId, 3).collect { resource ->
                when (resource) {
                    is Resource.Success -> {
                        val newModels = resource.data ?: emptyList()
                        println("Fetched models: $newModels")
                        if (newModels.isEmpty()) {
                            hasMore.value = false
                        } else {
                            _models.value = _models.value + newModels
                             page.value++
                        }
                    }
                    is Resource.Error -> {
                        println("Error fetching models: ${resource.message}")
                    }
                    is Resource.Loading -> {
                        println("Loading models...")
                    }
                }
                isLoading.value = false
            }
        }
    }
}