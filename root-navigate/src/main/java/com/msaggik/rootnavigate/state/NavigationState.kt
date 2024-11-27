package com.msaggik.rootnavigate.state

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController

class NavigationState(
    val navHostController: NavHostController
) {
    fun navigateTo(route: String) {
        navHostController.navigate(route) {
            launchSingleTop = true
            popUpTo(navHostController.graph.startDestinationId) {
                saveState = true
            }
            restoreState = true
        }
    }
}

@Composable
fun rememberNavigateState(
    navHostController: NavHostController = rememberNavController()
): NavigationState {
    return remember {
        NavigationState(navHostController)
    }
}
