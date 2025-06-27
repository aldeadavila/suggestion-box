package com.aldeadavila.suggestionbox.presentation.screens.client.travel.components

import android.icu.text.SimpleDateFormat
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowForward
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.DirectionsCar
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.aldeadavila.suggestionbox.domain.model.Travel
import java.util.*

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TravelItem(
    travel: Travel,
    navHostController: NavHostController
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp)
            .clickable {
                navHostController.navigate("travel/detail/${travel.toJson()}")
            },
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                // Tipo de viaje (Oferta/Solicitud)
                Text(
                    text = if (travel.type == Travel.OFFER) "Oferta" else "Solicitud",
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Bold,
                    color = if (travel.type == Travel.OFFER) MaterialTheme.colorScheme.primary 
                           else MaterialTheme.colorScheme.secondary
                )
                
                // Dirección del viaje
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.spacedBy(4.dp)
                ) {
                    Text(
                        text = if (travel.direction == Travel.TO_ALDEADAVILA) "Hacia" else "Desde",
                        fontSize = 14.sp
                    )
                    Text(
                        text = "Aldeadávila",
                        fontSize = 14.sp,
                        fontWeight = FontWeight.Bold
                    )
                    Icon(
                        imageVector = if (travel.direction == Travel.TO_ALDEADAVILA) 
                            Icons.Default.ArrowForward else Icons.Default.ArrowBack,
                        contentDescription = null,
                        modifier = Modifier.size(16.dp)
                    )
                }
            }

            Spacer(modifier = Modifier.height(8.dp))

            // Fecha
            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = "Fecha: ",
                    fontSize = 14.sp
                )
                Text(
                    text = SimpleDateFormat("dd/MM/yyyy HH:mm", Locale.getDefault())
                        .format(travel.date ?: Date()),
                    fontSize = 14.sp,
                    fontWeight = FontWeight.Bold
                )
            }

            if (travel.type == Travel.OFFER) {
                Spacer(modifier = Modifier.height(4.dp))
                // Plazas disponibles
                Row(
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Icon(
                        imageVector = Icons.Default.Person,
                        contentDescription = null,
                        modifier = Modifier.size(16.dp)
                    )
                    Spacer(modifier = Modifier.width(4.dp))
                    Text(
                        text = "${travel.seats} plazas disponibles",
                        fontSize = 14.sp
                    )
                }
            }

            Spacer(modifier = Modifier.height(8.dp))

            // Usuario
            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {
                Icon(
                    imageVector = Icons.Default.Person,
                    contentDescription = null,
                    modifier = Modifier.size(16.dp)
                )
                Spacer(modifier = Modifier.width(4.dp))
                Text(
                    text = travel.userName,
                    fontSize = 14.sp
                )
            }
        }
    }
} 