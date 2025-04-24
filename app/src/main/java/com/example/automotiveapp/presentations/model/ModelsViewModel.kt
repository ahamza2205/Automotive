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
    private val getModelsUseCase: GetModelsUseCase
) : ViewModel() {

    private val _models = MutableStateFlow<Resource<List<Model>>>(Resource.Loading())
    val models: StateFlow<Resource<List<Model>>> = _models

    fun getModels(page: Int, brandId: Int, categoryId: Int) {
        viewModelScope.launch {
            getModelsUseCase(page, brandId, categoryId).collect { result ->
                _models.value = result
            }
        }
    }
}