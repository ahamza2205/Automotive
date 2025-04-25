package com.example.automotiveapp.presentations.generation.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.AsyncImage
import com.example.automotiveapp.R
import com.example.automotiveapp.presentations.generation.viewmodel.GenerationViewModel
import com.example.automotiveapp.utils.Resource

@Composable
fun GenerationScreen(
    identificationAttributeId: Int,
    modelId: Int,
    identificationAttributeValueId: Int,
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
            3
        )
    }

    Box(modifier = Modifier.fillMaxSize()) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(
                    Brush.verticalGradient(
                        colors = listOf(colorResource(R.color.orange_background), Color.White)
                    )
                )
                .padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically
            ) {
                IconButton(onClick = onBackClick) {
                    Image(
                        painter = painterResource(id = R.drawable.ic_back),
                        contentDescription = "Back",
                        modifier = Modifier.size(80.dp)
                    )
                }

                Spacer(modifier = Modifier.weight(1f))

                when (vehiclesResource) {
                    is Resource.Success -> {
                        val vehicles = (vehiclesResource as Resource.Success).data ?: emptyList()
                        if (vehicles.isNotEmpty()) {
                            AsyncImage(
                                model = vehicles.first().brand_image,
                                contentDescription = vehicles.first().brand,
                                modifier = Modifier.size(100.dp)
                            )
                        } else {
                            Image(
                                painter = painterResource(id = R.drawable.placeholder),
                                contentDescription = "Placeholder",
                                modifier = Modifier.size(100.dp)
                            )
                        }
                    }
                    else -> {
                        Image(
                            painter = painterResource(id = R.drawable.placeholder),
                            contentDescription = "Placeholder",
                            modifier = Modifier.size(100.dp)
                        )
                    }
                }

                Spacer(modifier = Modifier.weight(1f))
            }

            when (vehiclesResource) {
                is Resource.Loading -> {
                    CircularProgressIndicator()
                }
                is Resource.Success -> {
                    val vehicles = (vehiclesResource as Resource.Success).data ?: emptyList()
                    LazyColumn {
                        items(vehicles.size) { index ->
                            val vehicle = vehicles[index]
                            val isFirstCard = index == 0
                            val isCompared = compareList.contains(vehicle) // Check if vehicle is in compare list
                            if (vehicle.extra_attributes?.isNotEmpty() == true) {
                                ExpandableVehicleCard(
                                    vehicle = vehicle,
                                    isFirstCard = isFirstCard,
                                    isCompared = isCompared,
                                    onCompareClick = { newState ->
                                        if (newState) {
                                            viewModel.addToCompare(vehicle)
                                        } else {
                                            viewModel.removeFromCompare(vehicle)
                                        }
                                    }
                                )
                            } else {
                                SimpleVehicleCard(
                                    vehicle = vehicle,
                                    isFirstCard = isFirstCard,
                                    isCompared = isCompared,
                                    onCompareClick = { newState ->
                                        if (newState) {
                                            viewModel.addToCompare(vehicle)
                                        } else {
                                            viewModel.removeFromCompare(vehicle)
                                        }
                                    }
                                )
                            }
                        }
                    }
                }
                is Resource.Error -> {
                    Text(
                        text = (vehiclesResource as Resource.Error).message ?: "Error occurred",
                        color = Color.Red
                    )
                }
            }
        }

        if (compareList.isNotEmpty()) {
            FloatingActionButton(
                onClick = { /* TODO: Navigate to Compare Screen */ },
                modifier = Modifier
                    .align(Alignment.BottomEnd)
                    .padding(16.dp),
                containerColor = colorResource(R.color.orange)
            ) {
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier.padding(horizontal = 8.dp)
                ) {
                    Icon(
                        painter = painterResource(id = R.drawable.compare_selected),
                        contentDescription = "Show Differences",
                        tint = Color.White,
                        modifier = Modifier.size(24.dp)
                    )
                    Spacer(modifier = Modifier.width(4.dp))
                    Text(
                        text = "${compareList.size}",
                        color = Color.White
                    )
                }
            }
        }
    }
}