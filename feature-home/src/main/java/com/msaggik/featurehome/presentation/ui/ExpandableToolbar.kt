package com.msaggik.featurehome.presentation.ui

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.Image
import androidx.compose.foundation.ScrollState
import androidx.compose.foundation.background
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.systemBarsPadding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp

private val HEADER_HEIGHT = 70.dp
private val TOOLBAR_HEIGHT = 50.dp
private const val DELAY_ANIMATION = 250

@Composable
fun ExpandableToolbar(
    scrollState: ScrollState,
    searchQuery: String,
    onQueryChange: (String)->Unit,
    modifier: Modifier = Modifier,
    locationName: String,
    content: @Composable () -> Unit,
) {
    val localDensity = LocalDensity.current
    val headerHeightPx = localDensity.density * HEADER_HEIGHT.value
    val toolbarHeightPx = localDensity.density * TOOLBAR_HEIGHT.value

    Box(modifier = modifier.background(MaterialTheme.colorScheme.background)) {
        content()
        HeaderHome(
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
            toolbarHeightPx = toolbarHeightPx
        )
    }
}

@Composable
private fun HeaderHome(
    searchQuery: String,
    onQueryChange: (String)->Unit,
    scroll: ScrollState,
    headerHeightPx: Float,
    modifier: Modifier = Modifier,
    locationName: String,
) {
    Box(
        modifier = modifier
            .graphicsLayer {
                translationY = -scroll.value.toFloat() / 3f
                alpha = (-1f / headerHeightPx) * 2 * scroll.value + 1
            }
    ) {
        Image(
            modifier = Modifier
                .fillMaxWidth()
                .height(headerHeightPx.dp),
            contentScale = ContentScale.Crop,
            painter = painterResource(id = com.msaggik.commonui.R.drawable.image_header),
            contentDescription = stringResource(id = com.msaggik.commonui.R.string.image_header_home)
        )
        Column(
            modifier = Modifier
                .systemBarsPadding()
                .fillMaxWidth()
                .padding(horizontal = 16.dp)
        ) {
            Spacer(modifier = Modifier.height(18.dp))
            Text(
                text = stringResource(id = com.msaggik.commonui.R.string.online_registration),
                style = MaterialTheme.typography.titleLarge,
                color = MaterialTheme.colorScheme.primary
            )
            Spacer(modifier = Modifier.height(18.dp))
            Text(
                text = "$locationName >",
                style = MaterialTheme.typography.bodyMedium,
                color = MaterialTheme.colorScheme.primary,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis
            )
            Spacer(modifier = Modifier.height(10.dp))
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(10.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                SearchEditText(
                    searchQuery = searchQuery,
                    onQueryChange = onQueryChange,

                )
                Icon(
                    modifier = Modifier.size(30.dp).padding(start = 5.dp),
                    painter = painterResource(id = com.msaggik.commonui.R.drawable.ic_location),
                    contentDescription = stringResource(com.msaggik.commonui.R.string.location),
                    tint = MaterialTheme.colorScheme.primary
                )
            }
        }
    }
}

@Composable
fun RowScope.SearchEditText(
    searchQuery: String,
    onQueryChange: (String)->Unit
) {
    val interactionSource = remember { MutableInteractionSource() }
    var searchQueryExample by remember { mutableStateOf("") }

    TextField(
        modifier = Modifier.weight(1f).height(50.dp),
        value = searchQueryExample,
        onValueChange = { newText ->
            searchQueryExample = newText
        },
        singleLine = true,
        enabled = true,
        shape = RoundedCornerShape(16.dp),
        interactionSource = interactionSource,
        placeholder = {
            Text(
                modifier = Modifier.weight(1f),
                text = stringResource(com.msaggik.commonui.R.string.search_hint),
                maxLines = 1,
                overflow = TextOverflow.Ellipsis,
                style = MaterialTheme.typography.bodyMedium,
                color = MaterialTheme.colorScheme.secondary,
                fontWeight = FontWeight.Medium,
            )
        },
        textStyle = TextStyle(color = MaterialTheme.colorScheme.onSecondary),
        colors = TextFieldDefaults.colors(
            focusedTextColor = MaterialTheme.colorScheme.secondary,
            unfocusedTextColor = MaterialTheme.colorScheme.secondary,
            disabledPlaceholderColor = MaterialTheme.colorScheme.onSecondary,
            focusedPlaceholderColor = MaterialTheme.colorScheme.onSecondary,
            unfocusedPlaceholderColor = MaterialTheme.colorScheme.onSecondary,
            focusedContainerColor = MaterialTheme.colorScheme.surface,
            unfocusedContainerColor = MaterialTheme.colorScheme.surface,
            focusedIndicatorColor = Color.Transparent,
            unfocusedIndicatorColor = Color.Transparent,
            cursorColor = MaterialTheme.colorScheme.onTertiary
        ),
        leadingIcon = {
            Icon(
                modifier = Modifier.size(25.dp).padding(start = 5.dp),
                painter = painterResource(id = com.msaggik.commonui.R.drawable.ic_search),
                contentDescription = stringResource(com.msaggik.commonui.R.string.search),
                tint = MaterialTheme.colorScheme.secondary
            )
        },
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun Toolbar(
    scroll: ScrollState,
    headerHeightPx: Float,
    toolbarHeightPx: Float,
    modifier: Modifier = Modifier,
) {
    val toolbarBottom by remember {
        mutableFloatStateOf(headerHeightPx - toolbarHeightPx * ((headerHeightPx / toolbarHeightPx) / 2))
    }

    val showToolbar by remember {
        derivedStateOf {
            scroll.value >= toolbarBottom
        }
    }

    val alpha by animateFloatAsState(
        targetValue = if (showToolbar) 1f else 0f,
        animationSpec = tween(durationMillis = DELAY_ANIMATION),
        label = stringResource(com.msaggik.commonui.R.string.animation_in_toolbar)
    )

    AnimatedVisibility(
        modifier = modifier,
        visible = showToolbar,
        enter = fadeIn(animationSpec = tween(DELAY_ANIMATION)),
        exit = fadeOut(animationSpec = tween(DELAY_ANIMATION))
    ) {
        TopAppBar(
            modifier = Modifier.alpha(alpha),
            title = {
                Text(
                    modifier = Modifier.fillMaxWidth(),
                    textAlign = TextAlign.Center,
                    style = MaterialTheme.typography.titleMedium,
                    fontWeight = FontWeight.Bold,
                    text = stringResource(com.msaggik.commonui.R.string.home)
                )
            },
            colors = TopAppBarDefaults.largeTopAppBarColors(containerColor = MaterialTheme.colorScheme.surface)
        )
    }
}
