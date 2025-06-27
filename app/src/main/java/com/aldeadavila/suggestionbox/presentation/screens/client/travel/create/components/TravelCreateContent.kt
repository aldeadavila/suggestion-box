package com.aldeadavila.suggestionbox.presentation.screens.client.travel.create.components

import android.app.DatePickerDialog
import android.app.TimePickerDialog
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.aldeadavila.suggestionbox.domain.model.Travel
import com.aldeadavila.suggestionbox.presentation.screens.client.travel.create.TravelCreateViewModel
import java.util.*

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TravelCreateContent(
    paddingValues: PaddingValues,
    vm: TravelCreateViewModel = hiltViewModel()
) {
    val context = LocalContext.current
    val calendar = Calendar.getInstance()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(paddingValues)
            .padding(horizontal = 20.dp)
            .verticalScroll(rememberScrollState()),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Spacer(modifier = Modifier.height(20.dp))

        // Tipo de viaje
        Text(
            text = "Tipo de viaje",
            fontSize = 17.sp,
            fontWeight = FontWeight.Bold
        )
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceEvenly
        ) {
            RadioButton(
                selected = vm.travelType == Travel.OFFER,
                onClick = { vm.onTravelTypeChange(Travel.OFFER) }
            )
            Text(text = "Oferta")
            RadioButton(
                selected = vm.travelType == Travel.REQUEST,
                onClick = { vm.onTravelTypeChange(Travel.REQUEST) }
            )
            Text(text = "Solicitud")
        }

        Spacer(modifier = Modifier.height(20.dp))

        // Dirección
        Text(
            text = "Dirección",
            fontSize = 17.sp,
            fontWeight = FontWeight.Bold
        )
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceEvenly
        ) {
            RadioButton(
                selected = vm.travelDirection == Travel.TO_ALDEADAVILA,
                onClick = { vm.onTravelDirectionChange(Travel.TO_ALDEADAVILA) }
            )
            Text(text = "Hacia Aldeadávila")
            RadioButton(
                selected = vm.travelDirection == Travel.FROM_ALDEADAVILA,
                onClick = { vm.onTravelDirectionChange(Travel.FROM_ALDEADAVILA) }
            )
            Text(text = "Desde Aldeadávila")
        }

        Spacer(modifier = Modifier.height(20.dp))

        // Fecha y hora
        OutlinedButton(
            onClick = {
                DatePickerDialog(
                    context,
                    { _, year, month, dayOfMonth ->
                        calendar.set(Calendar.YEAR, year)
                        calendar.set(Calendar.MONTH, month)
                        calendar.set(Calendar.DAY_OF_MONTH, dayOfMonth)

                        TimePickerDialog(
                            context,
                            { _, hourOfDay, minute ->
                                calendar.set(Calendar.HOUR_OF_DAY, hourOfDay)
                                calendar.set(Calendar.MINUTE, minute)
                                vm.onDateSelected(calendar.time)
                            },
                            calendar.get(Calendar.HOUR_OF_DAY),
                            calendar.get(Calendar.MINUTE),
                            true
                        ).show()
                    },
                    calendar.get(Calendar.YEAR),
                    calendar.get(Calendar.MONTH),
                    calendar.get(Calendar.DAY_OF_MONTH)
                ).show()
            }
        ) {
            Icon(
                imageVector = Icons.Default.DateRange,
                contentDescription = null
            )
            Spacer(modifier = Modifier.width(8.dp))
            Text(
                text = if (vm.selectedDate != null) {
                    java.text.SimpleDateFormat(
                        "dd/MM/yyyy HH:mm",
                        Locale.getDefault()
                    ).format(vm.selectedDate!!)
                } else {
                    "Seleccionar fecha y hora"
                }
            )
        }

        Spacer(modifier = Modifier.height(20.dp))

        // Plazas (solo para ofertas)
        if (vm.travelType == Travel.OFFER) {
            OutlinedTextField(
                value = vm.seats,
                onValueChange = { vm.onSeatsChange(it) },
                label = { Text("Plazas disponibles") },
                singleLine = true,
                modifier = Modifier.fillMaxWidth()
            )
            Spacer(modifier = Modifier.height(20.dp))
        }
        
        // Paradas
        OutlinedTextField(
            value = vm.paradasText,
            onValueChange = { vm.onParadasChange(it) },
            label = { Text("Paradas (separadas por coma)") },
            placeholder = { Text("Ej: Salamanca, Ledesma, Vitigudino") },
            modifier = Modifier.fillMaxWidth(),
            singleLine = false,
            maxLines = 3
        )
        
        Spacer(modifier = Modifier.height(20.dp))

        // Descripción
        OutlinedTextField(
            value = vm.description,
            onValueChange = { vm.onDescriptionChange(it) },
            label = { Text("Descripción (opcional)") },
            modifier = Modifier
                .fillMaxWidth()
                .height(120.dp),
            maxLines = 4
        )

        Spacer(modifier = Modifier.height(20.dp))

        Button(
            onClick = { vm.createTravel() },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Crear Viaje")
        }

        Spacer(modifier = Modifier.height(20.dp))
    }
} 