package com.example.automotiveapp.presentations.brand.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.automotiveapp.data.remote.Brand
import com.example.automotiveapp.domain.usecase.GetBrandsUseCase
import com.example.automotiveapp.utils.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class BrandsViewModel @Inject constructor(
    private val getBrandsUseCase: GetBrandsUseCase
) : ViewModel() {

    private val _brands = MutableStateFlow<Resource<List<Brand>>>(Resource.Loading())
    val brands: StateFlow<Resource<List<Brand>>> = _brands

    private val _searchText = MutableStateFlow("")
    val searchText: StateFlow<String> = _searchText

    val filteredBrands: StateFlow<List<Brand>?> = combine(_searchText, _brands) { text, resource ->
        val list = if (resource is Resource.Success) resource.data else emptyList()
        if (text.isBlank()) list
        else list?.filter { it.name.contains(text, ignoreCase = true) }
    }.stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), emptyList())

    fun fetchBrands(categoryId: Int) {
        viewModelScope.launch {
            getBrandsUseCase(categoryId).collect {
                _brands.value = it
            }
        }
    }

    fun onSearchTextChanged(newText: String) {
        _searchText.value = newText
    }
}
