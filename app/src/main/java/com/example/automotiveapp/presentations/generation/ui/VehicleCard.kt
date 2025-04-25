package com.example.automotiveapp.presentations.generation.ui

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.example.automotiveapp.data.remote.Vehicle
import com.example.automotiveapp.R
import com.example.automotiveapp.utils.formatPrice

@Composable
fun ExpandableVehicleCard(
    vehicle: Vehicle,
    isFirstCard: Boolean,
    isCompared: Boolean, // New parameter to track compare state
    onCompareClick: (Boolean) -> Unit // Modified to pass the new state
) {
    var expanded by remember { mutableStateOf(false) }

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp),
        shape = RoundedCornerShape(8.dp),
        elevation = CardDefaults.cardElevation(4.dp),
        colors = CardDefaults.cardColors(
            containerColor = colorResource(R.color.white)
        )
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            if (isFirstCard) {
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    AsyncImage(
                        model = vehicle.additional_images.firstOrNull(),
                        contentDescription = vehicle.name,
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(150.dp)
                    )
                    Text(
                        text = vehicle.model,
                        style = MaterialTheme.typography.titleMedium,
                        modifier = Modifier.padding(top = 8.dp)
                    )
                    Text(
                        text = vehicle.year,
                        style = MaterialTheme.typography.bodyLarge,
                        color = Color.Gray
                    )
                    Divider(
                        color = Color.LightGray,
                        thickness = 1.dp,
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(top = 8.dp)
                    )
                }
            }
            Spacer(modifier = Modifier.height(8.dp))
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.End
            ) {
                Column(
                    modifier = Modifier
                        .weight(1f)
                        .padding(8.dp),
                    horizontalAlignment = Alignment.Start
                ) {
                    Text(text = vehicle.name, fontSize = 16.sp, style = MaterialTheme.typography.bodyLarge)
                    Text(
                        text = "${formatPrice(vehicle.price)} EGP",
                        color = colorResource(R.color.orange),
                        fontSize = 16.sp,
                        style = MaterialTheme.typography.titleMedium
                    )
                }
                IconButton(onClick = { onCompareClick(!isCompared) }) {
                    Icon(
                        painter = painterResource(
                            id = if (isCompared) R.drawable.compare_selected else R.drawable.compare_unselected
                        ),
                        contentDescription = "Compare",
                        tint = if (isCompared) colorResource(R.color.orange) else Color.Gray,
                        modifier = Modifier.size(100.dp)
                    )
                }
                IconButton(onClick = { /* Favorite Action */ }) {
                    Icon(
                        painter = painterResource(id = R.drawable.star),
                        contentDescription = "Favorite",
                    )
                }
            }
            Divider(
                color = Color.LightGray,
                thickness = 1.dp,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 8.dp)
            )
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .clickable { expanded = !expanded }
                    .padding(vertical = 8.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Icon(
                    painter = if (expanded) painterResource(id = R.drawable.differences_icon) else  painterResource(id = R.drawable.group_117),
                    contentDescription = "Differences Icon",
                    tint = if (expanded) colorResource(R.color.orange) else Color.Gray,
                    modifier = Modifier.size(18.dp)
                )
                Spacer(modifier = Modifier.width(8.dp))
                Text(
                    text = "${vehicle.extra_attributes?.size ?: 0} Differences",
                    style = MaterialTheme.typography.bodyMedium,
                    fontSize = 14.sp,
                    color = if (expanded) colorResource(R.color.orange) else  Color.Gray,
                )
                Spacer(modifier = Modifier.weight(1f))
                Icon(
                    painter = painterResource(
                        id = if (expanded) R.drawable.ic_arrow_up else R.drawable.ic_arrow_down
                    ),
                    contentDescription = "Expand/Collapse",
                    tint = if (expanded) colorResource(R.color.orange) else Color.Gray,
                    modifier = Modifier.size(18.dp)
                )
            }
            AnimatedVisibility(visible = expanded) {
                Column(modifier = Modifier.padding(start = 28.dp)) {
                    vehicle.extra_attributes?.forEach { attribute ->
                        Text(text = "- $attribute", style = MaterialTheme.typography.bodySmall)
                    }
                }
            }

        }
    }
}

@Composable
fun SimpleVehicleCard(
    vehicle: Vehicle,
    isFirstCard: Boolean,
    isCompared: Boolean, // New parameter to track compare state
    onCompareClick: (Boolean) -> Unit // Modified to pass the new state
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp),
        shape = RoundedCornerShape(8.dp),
        elevation = CardDefaults.cardElevation(4.dp),
        colors = CardDefaults.cardColors(
            containerColor = colorResource(R.color.white)
        )
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            if (isFirstCard) {
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    AsyncImage(
                        model = vehicle.additional_images.firstOrNull(),
                        contentDescription = vehicle.name,
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(150.dp)
                    )
                    Text(
                        text = vehicle.model,
                        style = MaterialTheme.typography.titleMedium,
                        modifier = Modifier.padding(top = 8.dp)
                    )
                    Text(
                        text = vehicle.year,
                        style = MaterialTheme.typography.bodyLarge,
                        color = Color.Gray
                    )
                    Divider(
                        color = Color.LightGray,
                        thickness = 1.dp,
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(top = 8.dp)
                    )
                }
            }
            Spacer(modifier = Modifier.height(8.dp))
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.End
            ) {
                Column(
                    modifier = Modifier
                        .weight(1f),
                    horizontalAlignment = Alignment.Start
                ) {
                    Text(text = vehicle.name, fontSize = 16.sp, style = MaterialTheme.typography.bodyLarge)
                    Text(
                        text = "${formatPrice(vehicle.price)} EGP",
                        color = colorResource(R.color.orange),
                        fontSize = 16.sp,
                        style = MaterialTheme.typography.titleMedium
                    )
                }
                IconButton(onClick = { onCompareClick(!isCompared) }) {
                    Icon(
                        painter = painterResource(
                            id = if (isCompared) R.drawable.compare_selected else R.drawable.compare_unselected
                        ),
                        tint = if (isCompared) colorResource(R.color.orange) else Color.Gray,
                        contentDescription = "Compare",
                        modifier = Modifier.size(75.dp)
                    )
                }
                IconButton(onClick = { /* Favorite Action */ }) {
                    Icon(
                        painter = painterResource(id = R.drawable.star),
                        contentDescription = "Favorite",
                    )
                }
            }
        }
    }
}