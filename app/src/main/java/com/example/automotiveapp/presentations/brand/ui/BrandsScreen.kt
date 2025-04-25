package com.example.automotiveapp.presentations.brand.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.automotiveapp.presentations.brand.viewmodel.BrandsViewModel
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.res.colorResource
import androidx.navigation.NavController
import com.example.automotiveapp.R
import com.example.automotiveapp.utils.Routes

@Composable
fun BrandScreen(
    navController: NavController,
    viewModel: BrandsViewModel = hiltViewModel()
) {
    val searchText = viewModel.searchText.collectAsState().value
    val brands = viewModel.filteredBrands.collectAsState().value ?: emptyList()

    LaunchedEffect(Unit) {
        viewModel.fetchBrands(3)
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(
                brush = Brush.verticalGradient(
                    colors = listOf(colorResource(R.color.orange_background), Color.White)
                )
            )
            .padding(horizontal = 16.dp, vertical = 8.dp)
    ) {
        Text(
            text = "New Cars",
            style = MaterialTheme.typography.titleLarge,
            modifier = Modifier
                .align(Alignment.CenterHorizontally)
                .padding(top = 8.dp, bottom = 12.dp)
        )

        SearchBar(
            searchText = searchText,
            onSearchTextChange = { viewModel.onSearchTextChanged(it) }
        )

        LazyVerticalGrid(
            columns = GridCells.Fixed(4),
            modifier = Modifier.padding(top = 8.dp)
        ) {
            items(brands) { brand ->
                BrandItem(brand = brand, onClick = {
                    navController.currentBackStackEntry?.savedStateHandle?.set("brand", brand)
                    navController.navigate("${Routes.MODELS}/${brand.id}")
                })
            }
        }
    }
}




