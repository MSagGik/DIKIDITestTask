package com.msaggik.dikiditesttask.compose

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationRailItem
import androidx.compose.material3.NavigationRailItemColors
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.currentBackStackEntryAsState
import com.msaggik.commonui.theme.bodyFontFamily
import com.msaggik.featurehome.presentation.ui.HomeCompose
import com.msaggik.rootnavigate.graph.NavigationGraph
import com.msaggik.rootnavigate.state.NavigationState
import com.msaggik.rootnavigate.state.rememberNavigateState
import com.msaggik.rootnavigate.ui.ItemBottomNavigation

@Preview(showBackground = true, showSystemUi = true)
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainCompose() {

    val navigationState = rememberNavigateState()
    val scrollBehavior = TopAppBarDefaults.enterAlwaysScrollBehavior()

    Scaffold(
        modifier = Modifier
            .background(color = MaterialTheme.colorScheme.background)
            .nestedScroll(scrollBehavior.nestedScrollConnection),
        bottomBar = {
            BottomBar(navigationState)
        }
    ) {
        NavigationGraph(
            navController = navigationState.navHostController,
            homeContent = { HomeCompose(paddingValues = it) },
            catalogContent = { ColumnPlaceholder(com.msaggik.commonui.R.string.catalog_page) },
            promotionsContent = { ColumnPlaceholder(com.msaggik.commonui.R.string.promotion_page) },
            myEntriesContent = { ColumnPlaceholder(com.msaggik.commonui.R.string.my_entries_page) },
            moreContent = { ColumnPlaceholder(com.msaggik.commonui.R.string.more_page) }
        )
    }
}

@Composable
private fun BottomBar(
    navigationState: NavigationState,
) {
    val navBackStackEntry by navigationState.navHostController.currentBackStackEntryAsState()
    val currentRoute = navBackStackEntry?.destination?.route
    NavigationBar(
        modifier = Modifier.fillMaxWidth(),
        containerColor = MaterialTheme.colorScheme.surface
    ){
        val items = listOf(
            ItemBottomNavigation.Home,
            ItemBottomNavigation.Catalog,
            ItemBottomNavigation.Promotions,
            ItemBottomNavigation.MyEntries,
            ItemBottomNavigation.More
        )
        Row(
            modifier = Modifier
                .background(color = MaterialTheme.colorScheme.surface)
                .align(Alignment.CenterVertically).fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceAround
        ) {
            items.forEach { item ->
                val isSelected = currentRoute == item.itemMenu.route
                NavigationRailItem(
                    selected = isSelected,
                    onClick = { navigationState.navigateTo(item.itemMenu.route) },
                    icon = {
                        Icon(
                            imageVector = ImageVector.vectorResource(id = item.icon),
                            contentDescription = stringResource(id = item.label)
                        )
                    },
                    label = {
                        Text(
                            stringResource(id = item.label),
                            fontFamily = bodyFontFamily,
                        )
                    },
                    colors = NavigationRailItemColors(
                        selectedIconColor = MaterialTheme.colorScheme.onTertiary,
                        selectedTextColor = MaterialTheme.colorScheme.onTertiary,
                        selectedIndicatorColor = Color.Transparent,
                        unselectedIconColor = MaterialTheme.colorScheme.tertiary,
                        unselectedTextColor = MaterialTheme.colorScheme.tertiary,
                        disabledIconColor = MaterialTheme.colorScheme.tertiary,
                        disabledTextColor = MaterialTheme.colorScheme.tertiary
                    )
                )
            }
        }
    }
}

@Composable
private fun ColumnPlaceholder(textId: Int) {
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = stringResource(id = textId)
        )
    }
}
