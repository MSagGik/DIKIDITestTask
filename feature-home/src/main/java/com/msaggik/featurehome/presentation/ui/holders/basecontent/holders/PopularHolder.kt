package com.msaggik.featurehome.presentation.ui.holders.basecontent.holders

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.input.nestedscroll.NestedScrollConnection
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import coil.compose.SubcomposeAsyncImage
import com.msaggik.commonutils.all.Utils
import com.msaggik.featurehome.domain.model.location.Location
import com.msaggik.featurehome.domain.model.network.success.blocks.catalog.Catalog
import com.msaggik.featurehome.presentation.ui.holders.basecontent.common.Header
import com.msaggik.featurehome.presentation.viewmodel.HomeViewModel
import com.msaggik.featurehome.presentation.viewmodel.state.LocationState
import kotlinx.coroutines.launch
import org.koin.androidx.compose.koinViewModel

private const val TAG = "Calculation distance"

@Composable
internal fun PopularHolder(
    popularState: LazyListState,
    popularNestedScrollConnection: NestedScrollConnection,
    catalogList: List<Catalog>
) {
    Header(
        title = stringResource(com.msaggik.commonui.R.string.popular),
        number = catalogList.size.toString()
    )

    val configuration = LocalConfiguration.current
    val screenWidthDp = configuration.screenWidthDp.dp

    val homeViewModel: HomeViewModel = koinViewModel()
    val stateLocation by homeViewModel.stateLocation.observeAsState()
    val location = (stateLocation as? LocationState.Content)?.location

    val distances = remember { mutableStateListOf<Double>() }
    val coroutineScope = rememberCoroutineScope()
    LaunchedEffect(location, catalogList) {
        distances.clear()
        catalogList.forEach { item ->
            coroutineScope.launch {
                val distance = calculateDistance(location, item)
                distances.add(distance)
            }
        }
    }

    LazyRow(
        state = popularState,
        modifier = Modifier
            .fillMaxWidth()
            .nestedScroll(popularNestedScrollConnection),
        contentPadding = PaddingValues(horizontal = 16.dp),
        horizontalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        items(catalogList) {
            val index = catalogList.indexOf(it)
            val distance = distances.getOrNull(index) ?: -1.0
            Row(
                modifier = Modifier
                    .width(screenWidthDp - 32.dp)
                    .fillMaxWidth()
                    .wrapContentHeight()
                    .clip(RoundedCornerShape(16.dp))
                    .background(color = MaterialTheme.colorScheme.surface)
                    .padding(16.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                SubcomposeAsyncImage(
                    modifier = Modifier
                        .size(90.dp)
                        .clip(RoundedCornerShape(16.dp)),
                    contentScale = ContentScale.Crop,
                    model = it.image.thumb,
                    error = {
                        Image(
                            contentScale = ContentScale.Crop,
                            painter = painterResource(id = com.msaggik.commonui.R.drawable.catalog_placeholder),
                            contentDescription = stringResource(id = com.msaggik.commonui.R.string.error_load_image),
                        )
                    },
                    contentDescription = stringResource(id = com.msaggik.commonui.R.string.logo),
                )
                Column(
                    modifier = Modifier.fillMaxHeight(),
                    verticalArrangement = Arrangement.SpaceEvenly
                ) {
                    Row(
                        modifier = Modifier,
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.spacedBy(6.dp)
                    ) {
                        Text(
                            modifier = Modifier.padding(start = 19.dp),
                            text = it.rating.toString(),
                            style = MaterialTheme.typography.bodySmall,
                            color = MaterialTheme.colorScheme.tertiary,
                            fontWeight = FontWeight.Medium,
                        )
                        Icon(
                            modifier = Modifier.size(16.dp),
                            painter = painterResource(id = com.msaggik.commonui.R.drawable.ic_star),
                            contentDescription = stringResource(com.msaggik.commonui.R.string.star),
                            tint = MaterialTheme.colorScheme.surfaceVariant
                        )
                    }
                    Text(
                        modifier = Modifier.padding(horizontal = 19.dp),
                        text = it.name,
                        style = MaterialTheme.typography.bodyMedium,
                        color = MaterialTheme.colorScheme.onSurface,
                        maxLines = 1,
                        fontWeight = FontWeight.Bold,
                        overflow = TextOverflow.Ellipsis
                    )
                    Text(
                        modifier = Modifier.padding(horizontal = 19.dp),
                        text = it.street,
                        style = MaterialTheme.typography.bodySmall,
                        color = MaterialTheme.colorScheme.tertiary,
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis
                    )
                    if (distance != -1.0) {
                        Text(
                            modifier = Modifier.padding(horizontal = 19.dp),
                            text = stringResource(com.msaggik.commonui.R.string.form_distance, distance.toString()),
                            style = MaterialTheme.typography.bodySmall,
                            color = MaterialTheme.colorScheme.onTertiary,
                            maxLines = 1,
                            overflow = TextOverflow.Ellipsis
                        )
                    }
                }
            }
        }
    }
}

private suspend fun calculateDistance(
    location: Location?,
    item: Catalog
): Double {
    return runCatching {
        location?.let { currentLocation ->
            Utils.haversine(
                latitude1 = currentLocation.latitude,
                longitude1 = currentLocation.longitude,
                latitude2 = item.lat.toDouble(),
                longitude2 = item.lng.toDouble()
            )
        } ?: -1.0
    }.getOrElse { e ->
        Utils.outputStackTrace(TAG, e)
        -1.0
    }
}
