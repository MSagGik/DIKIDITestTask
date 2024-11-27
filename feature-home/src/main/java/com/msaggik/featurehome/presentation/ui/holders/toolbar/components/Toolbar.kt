package com.msaggik.featurehome.presentation.ui.holders.toolbar.components

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.FastOutSlowInEasing
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.ScrollState
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp

private const val DELAY_ANIMATION = 500

@OptIn(ExperimentalMaterial3Api::class)
@Composable
internal fun Toolbar(
    scroll: ScrollState,
    headerHeightPx: Float,
    toolbarHeightPx: Float,
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
        animationSpec = tween(
            durationMillis = DELAY_ANIMATION,
            easing = FastOutSlowInEasing
        ),
        label = stringResource(com.msaggik.commonui.R.string.animation_in_toolbar)
    )

    AnimatedVisibility(
        modifier = Modifier.background(MaterialTheme.colorScheme.background),
        visible = showToolbar,
        enter = fadeIn(animationSpec = tween(DELAY_ANIMATION)),
        exit = fadeOut(animationSpec = tween(DELAY_ANIMATION))
    ) {
        CenterAlignedTopAppBar(
            modifier = Modifier.alpha(alpha).padding(top = 5.dp),
            colors = TopAppBarDefaults.centerAlignedTopAppBarColors(containerColor = MaterialTheme.colorScheme.background),
            title = {
                Text(
                    text = stringResource(com.msaggik.commonui.R.string.home),
                    style = MaterialTheme.typography.titleLarge,
                    color = MaterialTheme.colorScheme.onSurface,
                    fontWeight = FontWeight.Bold,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis
                )
            }
        )
    }
}
