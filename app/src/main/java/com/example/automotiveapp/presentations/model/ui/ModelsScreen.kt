package com.example.automotiveapp.presentations.model.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import coil.compose.AsyncImage
import com.example.automotiveapp.R
import com.example.automotiveapp.data.remote.Brand
import com.example.automotiveapp.presentations.model.viewmodel.ModelsViewModel
import com.example.automotiveapp.presentations.model.viewmodel.ViewType
import com.example.automotiveapp.utils.Routes

@Composable
fun ModelsScreen(
    navController: NavController,
    viewModel: ModelsViewModel,
    brandId: Int,
    brand: Brand,
    onBackClick: () -> Unit,
) {
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
                    colors = listOf(colorResource(R.color.orange_background), Color.White)
                )
            )
            .padding(horizontal = 8.dp)
    ) {
     // Top App Bar
        Column(
            modifier = Modifier.fillMaxWidth()
        ) {
            Box(

                modifier = Modifier.fillMaxWidth()
                    .padding(top = 8.dp)
            ) {
                IconButton(
                    onClick = onBackClick, modifier = Modifier.align(Alignment.CenterStart)
                ) {
                    Image(
                        painter = painterResource(id = R.drawable.ic_back),
                        contentDescription = "Back",
                        modifier = Modifier.size(80.dp)
                    )
                }
                AsyncImage(
                    model = brand.image,
                    contentDescription = brand.name,
                    modifier = Modifier
                        .size(100.dp)
                        .align(Alignment.Center)
                )
            }
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 8.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                IconButton(
                    onClick = { viewModel.toggleViewType() },
                    modifier = Modifier
                        .padding(start = 4.dp)
                        .size(52.dp)
                        .background(
                            color = Color.White, shape = RoundedCornerShape(6.dp)
                        )
                ) {
                    val iconRes =
                        if (viewType == ViewType.Grid) R.drawable.ic_list else R.drawable.grid
                    Image(
                        painter = painterResource(id = iconRes),
                        contentDescription = "Toggle View",
                        modifier = Modifier.size(100.dp)
                    )
                }
                Spacer(modifier = Modifier.weight(1f))
                IconButton(
                    onClick = { /* Filter action */ },
                    modifier = Modifier
                        .padding(start = 4.dp)
                        .size(52.dp)
                        .background(
                            color = Color.White, shape = RoundedCornerShape(6.dp)
                        )
                ) {
                    Image(
                        painter = painterResource(id = R.drawable.sort), contentDescription = "Sort"
                    )
                }
                IconButton(
                    onClick = { /* Filter action */ },
                    modifier = Modifier
                        .padding(start = 4.dp)
                        .size(52.dp)
                        .background(
                            color = Color.White, shape = RoundedCornerShape(6.dp)
                        )
                ) {
                    Image(
                        painter = painterResource(id = R.drawable.find_a_car),
                        contentDescription = "Filter"
                    )
                }
            }
        }

        Spacer(modifier = Modifier.height(4.dp))

        if (viewType == ViewType.Grid) {
            LazyVerticalGrid(columns = GridCells.Fixed(2)) {
                items(models) { model ->
                    CustomGridModelCard(model = model) {
                        navController.navigate(
                            "${Routes.GENERATIONS}/${model.identification_attribute_id}/${model.identification_attribute_value_id}/${model.id}"
                        )
                    }
                }

            }
        } else {
            LazyColumn {
                items(models) { model ->
                    CustomListModelCard(model = model) {
                        navController.navigate(
                            "${Routes.GENERATIONS}/${model.identification_attribute_id}/${model.identification_attribute_value_id}/${model.id}"
                        )
                    }
                }
            }
        }
    }
}

