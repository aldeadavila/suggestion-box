package com.aldeadavila.suggestionbox.presentation.screens.client.travel.detail.components

import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.aldeadavila.suggestionbox.domain.model.Travel
import java.text.SimpleDateFormat
import java.util.*

@Composable
fun TravelDetailContent(
    paddingValues: PaddingValues,
    travel: Travel
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(paddingValues)
            .padding(horizontal = 20.dp),
        horizontalAlignment = Alignment.Start
    ) {
        Spacer(modifier = Modifier.height(20.dp))

        // Tipo y dirección
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            // Tipo de viaje
            Column {
                Text(
                    text = if (travel.type == Travel.OFFER) "Oferta" else "Solicitud",
                    fontSize = 24.sp,
                    fontWeight = FontWeight.Bold,
                    color = if (travel.type == Travel.OFFER) MaterialTheme.colorScheme.primary 
                           else MaterialTheme.colorScheme.secondary
                )
            }

            // Dirección
            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = if (travel.direction == Travel.TO_ALDEADAVILA) "Hacia" else "Desde",
                    fontSize = 16.sp
                )
                Spacer(modifier = Modifier.width(4.dp))
                Text(
                    text = "Aldeadávila",
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Bold
                )
                Icon(
                    imageVector = if (travel.direction == Travel.TO_ALDEADAVILA) 
                        Icons.Default.ArrowForward else Icons.Default.ArrowBack,
                    contentDescription = null,
                    modifier = Modifier.size(20.dp)
                )
            }
        }

        Spacer(modifier = Modifier.height(20.dp))

        // Fecha y hora
        Row(
            verticalAlignment = Alignment.CenterVertically
        ) {
            Icon(
                imageVector = Icons.Default.DateRange,
                contentDescription = null,
                modifier = Modifier.size(24.dp)
            )
            Spacer(modifier = Modifier.width(8.dp))
            Text(
                text = SimpleDateFormat("dd/MM/yyyy HH:mm", Locale.getDefault())
                    .format(travel.date ?: Date()),
                fontSize = 18.sp
            )
        }

        if (travel.type == Travel.OFFER) {
            Spacer(modifier = Modifier.height(16.dp))
            // Plazas disponibles
            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {
                Icon(
                    imageVector = Icons.Default.Person,
                    contentDescription = null,
                    modifier = Modifier.size(24.dp)
                )
                Spacer(modifier = Modifier.width(8.dp))
                Text(
                    text = "${travel.seats} plazas disponibles",
                    fontSize = 18.sp
                )
            }
        }

        Spacer(modifier = Modifier.height(16.dp))

        // Usuario
        Row(
            verticalAlignment = Alignment.CenterVertically
        ) {
            Icon(
                imageVector = Icons.Default.Person,
                contentDescription = null,
                modifier = Modifier.size(24.dp)
            )
            Spacer(modifier = Modifier.width(8.dp))
            Text(
                text = travel.userName,
                fontSize = 18.sp
            )
        }
        
        // Paradas
        if (travel.paradas.isNotEmpty()) {
            Spacer(modifier = Modifier.height(20.dp))
            
            Text(
                text = "Paradas",
                fontSize = 18.sp,
                fontWeight = FontWeight.Bold
            )
            
            Spacer(modifier = Modifier.height(8.dp))
            
            travel.paradas.forEach { parada ->
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier.padding(vertical = 4.dp)
                ) {
                    Icon(
                        imageVector = Icons.Default.Place,
                        contentDescription = null,
                        modifier = Modifier.size(16.dp)
                    )
                    Spacer(modifier = Modifier.width(8.dp))
                    Text(
                        text = parada,
                        fontSize = 16.sp
                    )
                }
            }
        }

        if (travel.description.isNotEmpty()) {
            Spacer(modifier = Modifier.height(20.dp))
            // Descripción
            Text(
                text = "Descripción",
                fontSize = 18.sp,
                fontWeight = FontWeight.Bold
            )
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                text = travel.description,
                fontSize = 16.sp
            )
        }
    }
} 