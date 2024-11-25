package com.msaggik.rootnavigate.ui

sealed class ItemBottomNavigation(
    val itemMenu: ItemMenu,
    val icon: Int,
    val label: Int
) {
    data object Home: ItemBottomNavigation(
        itemMenu = ItemMenu.Home,
        icon = com.msaggik.commonui.R.drawable.ic_home,
        label = com.msaggik.commonui.R.string.home
    )
    data object Catalog: ItemBottomNavigation(
        itemMenu = ItemMenu.Catalog,
        icon = com.msaggik.commonui.R.drawable.ic_catalog,
        label = com.msaggik.commonui.R.string.catalog
    )
    data object Promotions: ItemBottomNavigation(
        itemMenu = ItemMenu.Promotions,
        icon = com.msaggik.commonui.R.drawable.ic_promotions,
        label = com.msaggik.commonui.R.string.promotions
    )
    data object MyEntries: ItemBottomNavigation(
        itemMenu = ItemMenu.MyEntries,
        icon = com.msaggik.commonui.R.drawable.ic_my_entries,
        label = com.msaggik.commonui.R.string.my_entries
    )
    data object More: ItemBottomNavigation(
        itemMenu = ItemMenu.More,
        icon = com.msaggik.commonui.R.drawable.ic_more,
        label = com.msaggik.commonui.R.string.more
    )
}
