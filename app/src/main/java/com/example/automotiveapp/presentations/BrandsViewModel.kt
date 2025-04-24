package com.example.automotiveapp.presentations

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.automotiveapp.data.remote.Brand
import com.example.automotiveapp.domain.usecase.GetBrandsUseCase
import com.example.automotiveapp.utils.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class BrandsViewModel @Inject constructor(
    private val getBrandsUseCase: GetBrandsUseCase
) : ViewModel() {

    private val _brands = MutableStateFlow<Resource<List<Brand>>>(Resource.Loading())
    val brands: StateFlow<Resource<List<Brand>>> = _brands

    fun fetchBrands(categoryId: Int) {
        viewModelScope.launch {
            getBrandsUseCase(categoryId).collect {
                _brands.value = it
            }
        }
    }
}