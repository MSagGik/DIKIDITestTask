package com.msaggik.rootnavigate.ui

private const val ROUTE_HOME = "home"
private const val ROUTE_CATALOG = "catalog"
private const val ROUTE_PROMOTIONS = "promotion"
private const val ROUTE_MY_ENTRIES = "myEntries"
private const val ROUTE_MORE = "more"

sealed class ItemMenu(
    val route: String
) {
    data object Home : ItemMenu(ROUTE_HOME)
    data object Catalog : ItemMenu(ROUTE_CATALOG)
    data object Promotions : ItemMenu(ROUTE_PROMOTIONS)
    data object MyEntries : ItemMenu(ROUTE_MY_ENTRIES)
    data object More : ItemMenu(ROUTE_MORE)
}
