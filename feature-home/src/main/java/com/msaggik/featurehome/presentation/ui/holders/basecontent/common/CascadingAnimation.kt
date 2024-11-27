package com.msaggik.featurehome.presentation.ui.holders.basecontent.common

import androidx.compose.animation.core.*
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.delay

@Composable
fun CascadingAnimation(isLoading: Boolean) {
    val images = listOf(
        com.msaggik.commonui.R.drawable.ic_home,
        com.msaggik.commonui.R.drawable.ic_catalog,
        com.msaggik.commonui.R.drawable.ic_promotions,
        com.msaggik.commonui.R.drawable.ic_my_entries,
        com.msaggik.commonui.R.drawable.ic_more
    )

    var currentIndex by remember { mutableIntStateOf(0) }
    val alphaAnimation = remember { Animatable(0f) }

    LaunchedEffect(currentIndex) {
        while (isLoading) {
            alphaAnimation.animateTo(1f, animationSpec = tween(durationMillis = 50))
            delay(100)
            alphaAnimation.animateTo(0f, animationSpec = tween(durationMillis = 50))
            currentIndex = (currentIndex + 1) % images.size
        }
    }

    Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.Center
        ) {
            images.forEachIndexed { index, imageRes ->
                Image(
                    painter = painterResource(id = imageRes),
                    contentDescription = null,
                    colorFilter = ColorFilter.tint(MaterialTheme.colorScheme.onTertiary),
                    modifier = Modifier
                        .size(35.dp)
                        .alpha(if (index == currentIndex) alphaAnimation.value else 0f)
                )
            }
        }
    }
}
