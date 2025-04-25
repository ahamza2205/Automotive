package com.example.automotiveapp.presentations.generation

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.example.automotiveapp.data.remote.Vehicle
import com.example.automotiveapp.R


@Composable
fun ExpandableVehicleCard(vehicle: Vehicle, onCompareClick: () -> Unit) {
    var expanded by remember { mutableStateOf(false) }

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp),
        shape = RoundedCornerShape(8.dp),
        elevation = CardDefaults.cardElevation(4.dp)
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            AsyncImage(
                model = vehicle.additional_images.firstOrNull(),
                contentDescription = vehicle.name,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(150.dp)
            )
            Text(text = vehicle.name, style = MaterialTheme.typography.titleMedium)
            Text(text = "${vehicle.price} EGP", style = MaterialTheme.typography.bodyMedium)
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Button(onClick = onCompareClick) {
                    Text("Compare")
                }
                IconButton(onClick = { /* Favorite Action */ }) {
                    Icon(
                        painter = painterResource(id = R.drawable.star),
                        contentDescription = "Favorite"
                    )
                }
            }
            AnimatedVisibility(visible = expanded) {
                Column {
                    vehicle.extra_attributes?.forEach { attribute ->
                        Text(text = "- $attribute", style = MaterialTheme.typography.bodySmall)
                    }
                }
            }
            TextButton(onClick = { expanded = !expanded }) {
                Text(if (expanded) "Hide" else "Show More")
            }
        }
    }
}

@Composable
fun SimpleVehicleCard(vehicle: Vehicle, onCompareClick: () -> Unit) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp),
        shape = RoundedCornerShape(8.dp),
        elevation = CardDefaults.cardElevation(4.dp)
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            AsyncImage(
                model = vehicle.additional_images.firstOrNull(),
                contentDescription = vehicle.name,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(150.dp)
            )
            Text(text = vehicle.name, style = MaterialTheme.typography.titleMedium)
            Text(text = "${vehicle.price} EGP", style = MaterialTheme.typography.bodyMedium)
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Button(onClick = onCompareClick) {
                    Text("Compare")
                }
                IconButton(onClick = { /* Favorite Action */ }) {
                    Icon(
                        painter = painterResource(id = R.drawable.star),
                        contentDescription = "Favorite"
                    )
                }
            }
        }
    }
}