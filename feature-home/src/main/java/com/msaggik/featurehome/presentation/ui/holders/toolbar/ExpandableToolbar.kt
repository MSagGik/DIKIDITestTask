package com.msaggik.featurehome.presentation.ui.holders.toolbar

import androidx.compose.foundation.ScrollState
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.unit.dp
import com.msaggik.featurehome.presentation.ui.holders.toolbar.components.HeaderToolbar
import com.msaggik.featurehome.presentation.ui.holders.toolbar.components.Toolbar

private val HEADER_HEIGHT = 70.dp
private val TOOLBAR_HEIGHT = 50.dp

@Composable
internal fun ExpandableToolbar(
    scrollState: ScrollState,
    searchQuery: String,
    onQueryChange: (String) -> Unit,
    modifier: Modifier = Modifier,
    locationName: String,
    content: @Composable () -> Unit,
) {
    val localDensity = LocalDensity.current
    val headerHeightPx = localDensity.density * HEADER_HEIGHT.value
    val toolbarHeightPx = localDensity.density * TOOLBAR_HEIGHT.value

    Box(modifier = modifier.background(MaterialTheme.colorScheme.background)) {
        content()
        HeaderToolbar(
            searchQuery = searchQuery,
            onQueryChange = onQueryChange,
            scroll = scrollState,
            headerHeightPx = headerHeightPx,
            modifier = Modifier.fillMaxWidth(),
            locationName = locationName
        )
        Toolbar(
            scroll = scrollState,
            headerHeightPx = headerHeightPx,
            toolbarHeightPx = toolbarHeightPx,
        )
    }
}
