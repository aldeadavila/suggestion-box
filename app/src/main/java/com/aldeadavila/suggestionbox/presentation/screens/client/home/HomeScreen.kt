package com.aldeadavila.suggestionbox.presentation.screens.client.home

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.List
import androidx.compose.material.icons.filled.Flight
import androidx.compose.material.icons.filled.Map
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.ThumbUp
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.DrawerState
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.aldeadavila.suggestionbox.R
import com.aldeadavila.suggestionbox.presentation.components.DefaultTopBar
import com.aldeadavila.suggestionbox.presentation.navigation.HomeBottomBarScreen

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(
    navController: NavHostController,
    drawerState: DrawerState? = null
) {
    Scaffold(
        topBar = {
            DefaultTopBar(
                title = "Aldeadávila y Corporario",
                drawerState = drawerState
            )
        }
    ) { paddingValues ->
        HomeContent(
            paddingValues = paddingValues,
            navController = navController
        )
    }
}

@Composable
fun HomeContent(
    paddingValues: PaddingValues,
    navController: NavHostController
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(paddingValues)
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        // Logo de la asociación en la parte superior
        Image(
            painter = painterResource(id = R.drawable.ic_small_logo_asociacion_vecinos),
            contentDescription = "Logo Asociación de Vecinos",
            modifier = Modifier
                .size(120.dp)
                .clip(CircleShape)
                .background(MaterialTheme.colorScheme.primary.copy(alpha = 0.1f))
                .padding(16.dp),
            contentScale = ContentScale.Fit
        )
        
        Spacer(modifier = Modifier.height(24.dp))
        
        Text(
            text = "Bienvenido a la asociación de vecinos",
            fontSize = 20.sp,
            fontWeight = FontWeight.Bold,
            textAlign = TextAlign.Center
        )
        
        Spacer(modifier = Modifier.height(8.dp))
        
        Text(
            text = "Selecciona una opción para comenzar",
            fontSize = 16.sp,
            color = Color.Gray,
            textAlign = TextAlign.Center
        )
        
        Spacer(modifier = Modifier.height(32.dp))
        
        // Grid de características
        val features = listOf(
            FeatureItem(
                title = "Sugerencias",
                description = "Envía tus ideas y propuestas para mejorar nuestro pueblo",
                icon = Icons.Default.ThumbUp,
                route = HomeBottomBarScreen.SuggestionList.route,
                color = MaterialTheme.colorScheme.primary
            ),
            FeatureItem(
                title = "Viajar",
                description = "Comparte o busca viajes con otros vecinos",
                icon = Icons.Default.Flight,
                route = HomeBottomBarScreen.Travel.route,
                color = MaterialTheme.colorScheme.secondary
            ),
            FeatureItem(
                title = "Mapa",
                description = "Descubre puntos de interés y rutas de senderismo",
                icon = Icons.Default.Map,
                route = HomeBottomBarScreen.Map.route,
                color = MaterialTheme.colorScheme.tertiary
            ),
            FeatureItem(
                title = "Noticias",
                description = "Mantente informado de las últimas novedades",
                icon = Icons.AutoMirrored.Filled.List,
                route = HomeBottomBarScreen.NewsList.route,
                color = MaterialTheme.colorScheme.error
            ),
            FeatureItem(
                title = "Perfil",
                description = "Gestiona tu información personal",
                icon = Icons.Default.Person,
                route = HomeBottomBarScreen.Profile.route,
                color = MaterialTheme.colorScheme.surfaceTint
            )
        )
        
        LazyVerticalGrid(
            columns = GridCells.Fixed(2),
            horizontalArrangement = Arrangement.spacedBy(12.dp),
            verticalArrangement = Arrangement.spacedBy(12.dp),
            modifier = Modifier.padding(4.dp)
        ) {
            items(features) { feature ->
                FeatureCard(
                    feature = feature,
                    onClick = {
                        navController.navigate(feature.route)
                    }
                )
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FeatureCard(
    feature: FeatureItem,
    onClick: () -> Unit
) {
    ElevatedCard(
        onClick = onClick,
        modifier = Modifier
            .fillMaxWidth()
            .height(130.dp),
        shape = RoundedCornerShape(12.dp),
        colors = CardDefaults.elevatedCardColors(
            containerColor = MaterialTheme.colorScheme.surface
        ),
        elevation = CardDefaults.elevatedCardElevation(defaultElevation = 4.dp)
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(12.dp),
        ) {
            // Icono y título en la parte superior
            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {
                // Icono
                Box(
                    modifier = Modifier
                        .size(32.dp)
                        .clip(CircleShape)
                        .background(feature.color.copy(alpha = 0.2f)),
                    contentAlignment = Alignment.Center
                ) {
                    Icon(
                        imageVector = feature.icon,
                        contentDescription = feature.title,
                        tint = feature.color,
                        modifier = Modifier.size(18.dp)
                    )
                }
                
                Spacer(modifier = Modifier.width(8.dp))
                
                // Título
                Text(
                    text = feature.title,
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Bold,
                    color = MaterialTheme.colorScheme.onSurface
                )
            }
            
            Spacer(modifier = Modifier.height(8.dp))
            
            // Descripción
            Text(
                text = feature.description,
                fontSize = 12.sp,
                color = MaterialTheme.colorScheme.onSurfaceVariant,
                maxLines = 3,
                overflow = TextOverflow.Ellipsis
            )
        }
    }
}

data class FeatureItem(
    val title: String,
    val description: String,
    val icon: ImageVector,
    val route: String,
    val color: Color
) 