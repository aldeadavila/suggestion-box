package com.aldeadavila.suggestionbox.presentation.screens.client.home

import android.annotation.SuppressLint
import androidx.compose.material.*
import androidx.compose.foundation.layout.RowScope
import androidx.compose.material.SnackbarDefaults.backgroundColor
import androidx.compose.material3.Icon
import androidx.compose.material3.LocalContentColor
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.sp
import androidx.navigation.NavDestination
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.aldeadavila.suggestionbox.presentation.navigation.HomeBottomBarNavGraph
import com.aldeadavila.suggestionbox.presentation.navigation.HomeBottomBarScreen

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun ClientHomeScreen(navHostController: NavHostController = rememberNavController()
) {
    Scaffold (
        bottomBar = { BottomBar(navController = navHostController) }
    ){
        HomeBottomBarNavGraph(navController = navHostController)
    }

}

@Composable
fun BottomBar(navController: NavHostController) {
    val screens = listOf(
        HomeBottomBarScreen.SuggestionList,
        HomeBottomBarScreen.Map,
        HomeBottomBarScreen.NewsList,
        HomeBottomBarScreen.Profile,
    )

    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentDestination = navBackStackEntry?.destination
    val bottomBarDestination = screens.any { it.route == currentDestination?.route }

    if (bottomBarDestination) {

        BottomNavigation(
            backgroundColor = MaterialTheme.colors.secondary,
        ){
            screens.forEach { screen ->
                AddItem(
                    screen = screen,
                    currentDestination = currentDestination,
                    navController = navController,

                )
            }
        }

    }

}

@Composable
fun RowScope.AddItem(
    screen: HomeBottomBarScreen,
    currentDestination: NavDestination?,
    navController: NavHostController
) {

    BottomNavigationItem(
        label = {
            Text(
                text = screen.title,
                fontSize = 10.sp
                )
        },
        icon = {
            Icon(
                imageVector = screen.icon,
                contentDescription = "Navigation icon"
            )
        },
        selected = currentDestination?.hierarchy?.any {
            it.route == screen.route
        } == true,
        unselectedContentColor = LocalContentColor.current.copy(alpha = ContentAlpha.disabled),
        onClick = {
            navController.navigate(screen.route) {
                popUpTo(navController.graph.findStartDestination().id)
                launchSingleTop = true
            }
        }
    )

}