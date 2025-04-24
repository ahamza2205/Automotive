package com.example.automotiveapp.presentations.model

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.example.automotiveapp.data.remote.Model

@Composable
fun ModelGridItem(model: Model) {
    Card(
        modifier = Modifier
            .padding(8.dp)
            .fillMaxWidth(),
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
    ) {
        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            AsyncImage(
                model = model.image,
                contentDescription = model.name,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(120.dp)
            )
            Text(text = model.name, style = MaterialTheme.typography.bodyLarge)
            Text(text = "Starting from ${model.price} EGP", color = Color(0xFFFF5722))
        }
    }
}

@Composable
fun ModelListItem(model: Model) {
    Card(
        modifier = Modifier
            .padding(8.dp)
            .fillMaxWidth(),
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
    ) {
        Row(verticalAlignment = Alignment.CenterVertically) {
            AsyncImage(
                model = model.image,
                contentDescription = model.name,
                modifier = Modifier
                    .width(100.dp)
                    .height(100.dp)
            )
            Column(modifier = Modifier.padding(start = 16.dp)) {
                Text(text = model.name, style = MaterialTheme.typography.bodyLarge)
                Text(text = "Starting from ${model.price} EGP", color = Color(0xFFFF5722))
            }
        }
    }
}
