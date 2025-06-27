package com.aldeadavila.suggestionbox.presentation.screens.client.travel.detail

import android.annotation.SuppressLint
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.aldeadavila.suggestionbox.domain.model.Travel
import com.aldeadavila.suggestionbox.presentation.screens.client.travel.detail.components.DeleteTravel
import com.aldeadavila.suggestionbox.presentation.screens.client.travel.detail.components.TravelDetailContent
import com.aldeadavila.suggestionbox.presentation.screens.client.travel.TravelViewModel
import androidx.hilt.navigation.compose.hiltViewModel

@OptIn(ExperimentalMaterial3Api::class)
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun TravelDetailScreen(
    navController: NavHostController,
    travelParam: String,
    vm: TravelViewModel = hiltViewModel()
) {
    val travel = Travel.fromJson(travelParam)

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        text = if (travel.type == Travel.OFFER) "Oferta de viaje" else "Solicitud de viaje",
                        fontSize = 19.sp
                    )
                },
                navigationIcon = {
                    IconButton(onClick = { navController.popBackStack() }) {
                        Icon(
                            imageVector = Icons.Default.ArrowBack,
                            contentDescription = "Volver",
                            tint = Color.White
                        )
                    }
                },
                actions = {
                    // Solo mostrar acciones si el usuario es el propietario
                    if (travel.userId == com.google.firebase.auth.FirebaseAuth.getInstance().currentUser?.uid) {
                        IconButton(onClick = {
                            navController.navigate("travel/update/${travelParam}")
                        }) {
                            Icon(
                                imageVector = Icons.Default.Edit,
                                contentDescription = "Editar",
                                tint = Color.White
                            )
                        }
                        IconButton(onClick = {
                            vm.deleteTravel(travel.id)
                            navController.popBackStack()
                        }) {
                            Icon(
                                imageVector = Icons.Default.Delete,
                                contentDescription = "Eliminar",
                                tint = Color.White
                            )
                        }
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = MaterialTheme.colorScheme.surfaceColorAtElevation(4.dp)
                )
            )
        }
    ) {
        TravelDetailContent(paddingValues = it, travel = travel)
    }
    DeleteTravel()
} 