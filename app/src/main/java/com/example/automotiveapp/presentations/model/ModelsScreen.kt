package com.example.automotiveapp.presentations.model

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.automotiveapp.R

@Composable
fun ModelsScreen(viewModel: ModelsViewModel, brandId: Int) {
    val viewType by viewModel.viewType.collectAsState()
    val models by viewModel.models.collectAsState()

    LaunchedEffect(Unit) {
        viewModel.fetchModels(brandId)
    }
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(
                Brush.verticalGradient(
                    colors = listOf(Color(0xFFFFE5D0), Color.White)
                )
            )
            .padding(horizontal = 16.dp, vertical = 8.dp)
    ) {
        // Top Row with Back and Toggle Icons
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            // Back Button with Custom Image
            IconButton(onClick = { /* Handle back */ }) {
                Image(
                    painter = painterResource(id = R.drawable.ic_back),
                    contentDescription = "Back",
                    modifier = Modifier.size(24.dp)
                )
            }

            Text(
                text = "Models",
                style = MaterialTheme.typography.titleLarge
            )

            // Toggle Button with Custom Images
            IconButton(onClick = { viewModel.toggleViewType() }) {
                val iconRes =
                    if (viewType == ViewType.Grid) R.drawable.ic_list else R.drawable.ic_grid
                Image(
                    painter = painterResource(id = iconRes),
                    contentDescription = "Toggle View",
                    modifier = Modifier.size(24.dp)
                )
            }
        }

        Spacer(modifier = Modifier.height(8.dp))

        // List or Grid
        if (viewType == ViewType.Grid) {
            LazyVerticalGrid(columns = GridCells.Fixed(2)) {
                items(models) { model ->
                    ModelGridItem(model = model)
                }
            }
        } else {
            LazyColumn {
                items(models) { model ->
                    ModelListItem(model = model)
                }
            }
        }
    }
}
