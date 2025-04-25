package com.example.automotiveapp.presentations.generation

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.automotiveapp.R
import com.example.automotiveapp.utils.Resource


@Composable
fun GenerationScreen(
    identificationAttributeId: Int,
    modelId: Int,
    identificationAttributeValueId: Int,
    category: Int,
    onBackClick: () -> Unit,
    viewModel: GenerationViewModel = hiltViewModel()
) {
    val vehiclesResource by viewModel.vehicles.collectAsState()
    val compareList by viewModel.compareList.collectAsState()

    LaunchedEffect(Unit) {
        viewModel.fetchVehicles(
            identificationAttributeId,
            modelId,
            identificationAttributeValueId,
            category
        )
    }

    Box(modifier = Modifier.fillMaxSize()) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(Color.White)
                .padding(16.dp)
        ) {
            IconButton(onClick = onBackClick) {
                Icon(
                    painter = painterResource(id = R.drawable.ic_back),
                    contentDescription = "Back"
                )
            }

            when (vehiclesResource) {
                is Resource.Loading -> {
                    CircularProgressIndicator(modifier = Modifier.align(Alignment.CenterHorizontally))
                }
                is Resource.Success -> {
                    val vehicles = (vehiclesResource as Resource.Success).data ?: emptyList()
                    LazyColumn {
                        items(vehicles.size) { index ->
                            val vehicle = vehicles[index]
                            if (vehicle.extra_attributes?.isNotEmpty() == true) {
                                ExpandableVehicleCard(vehicle = vehicle, onCompareClick = { viewModel.addToCompare(vehicle) })
                            } else {
                                SimpleVehicleCard(vehicle = vehicle, onCompareClick = { viewModel.addToCompare(vehicle) })
                            }
                        }
                    }
                }
                is Resource.Error -> {
                    Text(
                        text = (vehiclesResource as Resource.Error).message ?: "error",
                        color = Color.Red,
                        modifier = Modifier.align(Alignment.CenterHorizontally)
                    )
                }
            }
        }

        if (compareList.isNotEmpty()) {
            FloatingActionButton(
                onClick = { /* Navigate to Compare Screen */ },
                modifier = Modifier
                    .align(Alignment.BottomEnd)
                    .padding(16.dp),
                containerColor = Color(0xFFFF9800)
            ) {
                Text(text = "${compareList.size}", color = Color.White)
            }
        }
    }
}