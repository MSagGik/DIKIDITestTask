package com.msaggik.featurehome.presentation.ui.holders.toolbar.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.ScrollState
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.systemBarsPadding
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp

@Composable
internal fun HeaderToolbar(
    searchQuery: String,
    onQueryChange: (String) -> Unit,
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
                .height(210.dp),
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
            Spacer(
                modifier = Modifier
                    .height(18.dp)
                    .background(Color.Transparent)
            )
            Text(
                modifier = Modifier.background(Color.Transparent),
                text = stringResource(id = com.msaggik.commonui.R.string.online_registration),
                style = MaterialTheme.typography.titleLarge,
                color = MaterialTheme.colorScheme.primary
            )
            Spacer(modifier = Modifier.height(18.dp))
            Text(
                modifier = Modifier.background(Color.Transparent),
                text = "$locationName >",
                style = MaterialTheme.typography.bodyMedium,
                color = MaterialTheme.colorScheme.primary,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis
            )
            Spacer(modifier = Modifier.height(10.dp))
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(Color.Transparent),
                horizontalArrangement = Arrangement.spacedBy(10.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                SearchEditText(
                    searchQuery = searchQuery,
                    onQueryChange = onQueryChange,
                )
                Icon(
                    modifier = Modifier
                        .size(30.dp)
                        .padding(start = 5.dp),
                    painter = painterResource(id = com.msaggik.commonui.R.drawable.ic_location),
                    contentDescription = stringResource(com.msaggik.commonui.R.string.location),
                    tint = MaterialTheme.colorScheme.primary
                )
            }
        }
    }
}
