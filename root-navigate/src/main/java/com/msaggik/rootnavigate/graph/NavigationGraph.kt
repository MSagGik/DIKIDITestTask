package com.msaggik.rootnavigate.graph

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.msaggik.rootnavigate.ui.ItemMenu

@Composable
fun NavigationGraph(
    navController: NavHostController,
    homeContent: @Composable () -> Unit,
    catalogContent: @Composable () -> Unit,
    promotionsContent: @Composable () -> Unit,
    myEntriesContent: @Composable () -> Unit,
    moreContent: @Composable () -> Unit,
) {
    NavHost(
        navController = navController,
        startDestination = ItemMenu.Home.route,
        Modifier.fillMaxSize()
    ) {
        composable(ItemMenu.Home.route) {
            homeContent()
        }
        composable(ItemMenu.Catalog.route) {
            catalogContent()
        }
        composable(ItemMenu.Promotions.route) {
            promotionsContent()
        }
        composable(ItemMenu.MyEntries.route) {
            myEntriesContent()
        }
        composable(ItemMenu.More.route) {
            moreContent()
        }
    }
}
