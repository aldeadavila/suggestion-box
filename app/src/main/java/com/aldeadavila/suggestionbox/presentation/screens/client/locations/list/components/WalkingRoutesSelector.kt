package com.aldeadavila.suggestionbox.presentation.screens.client.locations.list.components

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.aldeadavila.suggestionbox.domain.model.WalkingRoute
import com.aldeadavila.suggestionbox.presentation.screens.client.locations.list.WalkingRoutesViewModel

@Composable
fun WalkingRoutesSelector(
    viewModel: WalkingRoutesViewModel,
    modifier: Modifier = Modifier
) {
    var expanded by remember { mutableStateOf(false) }
    val rotationState by animateFloatAsState(
        targetValue = if (expanded) 180f else 0f
    )
    
    Card(
        modifier = modifier
            .padding(8.dp),
        shape = RoundedCornerShape(8.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp)
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .clickable { expanded = !expanded }
                    .padding(8.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(
                    text = "Rutas de senderismo",
                    fontWeight = FontWeight.Bold,
                    fontSize = 16.sp
                )
                
                Icon(
                    imageVector = Icons.Default.KeyboardArrowDown,
                    contentDescription = if (expanded) "Contraer" else "Expandir",
                    modifier = Modifier.rotate(rotationState)
                )
            }
            
            AnimatedVisibility(visible = expanded) {
                if (viewModel.routes.isEmpty()) {
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(16.dp),
                        contentAlignment = Alignment.Center
                    ) {
                        Text(
                            text = "No hay rutas disponibles",
                            color = Color.Gray
                        )
                    }
                } else {
                    LazyColumn(
                        modifier = Modifier
                            .fillMaxWidth()
                            .heightIn(max = 200.dp)
                    ) {
                        items(viewModel.routes) { route ->
                            RouteItem(
                                route = route,
                                onVisibilityChanged = { isVisible ->
                                    viewModel.updateRouteVisibility(route.id, isVisible)
                                }
                            )
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun RouteItem(
    route: WalkingRoute,
    onVisibilityChanged: (Boolean) -> Unit
) {
    var isVisible by remember { mutableStateOf(route.isVisible) }
    
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 4.dp, horizontal = 8.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Box(
            modifier = Modifier
                .size(16.dp)
                .clip(CircleShape)
                .background(Color(route.color))
        )
        
        Column(
            modifier = Modifier
                .weight(1f)
                .padding(start = 12.dp)
        ) {
            Text(
                text = route.name,
                fontWeight = FontWeight.Medium,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis
            )
            
            val elevationText = if (route.elevationGain > 0) {
                " • ↑ ${route.elevationGain.toInt()} m"
            } else {
                ""
            }
            
            Text(
                text = "${String.format("%.2f", route.distance)} km • ${route.duration} min • ${route.difficulty}$elevationText",
                fontSize = 12.sp,
                color = Color.Gray,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis
            )
        }
        
        Switch(
            checked = isVisible,
            onCheckedChange = { 
                isVisible = it
                onVisibilityChanged(it)
            }
        )
    }
} 