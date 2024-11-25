package com.msaggik.featurehome.presentation.ui

import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.ScrollState
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyHorizontalGrid
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.nestedscroll.NestedScrollConnection
import androidx.compose.ui.input.nestedscroll.NestedScrollSource
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import coil.compose.SubcomposeAsyncImage
import com.msaggik.commonutils.network.HttpStatus
import com.msaggik.featurehome.domain.model.success.SuccessHomeResponse
import com.msaggik.featurehome.domain.model.success.blocks.catalog.Catalog
import com.msaggik.featurehome.presentation.viewmodel.HomeViewModel
import com.msaggik.featurehome.presentation.viewmodel.state.HomeState
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch
import org.koin.androidx.compose.koinViewModel
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Icon
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.withStyle
import coil.compose.AsyncImage
import com.msaggik.commonutils.all.Utils
import com.msaggik.featurehome.domain.model.success.blocks.shares.Share
import com.msaggik.featurehome.domain.model.success.blocks.vip.Vip
import java.util.Locale

private const val SCROLL_THRESHOLD = 30
private val HEADER_HEIGHT = 70.dp
private val TOOLBAR_HEIGHT = 50.dp

@Composable
fun HomeCompose(
    paddingValues: PaddingValues
) {
    val homeViewModel: HomeViewModel = koinViewModel()
    val homeState by homeViewModel.homeStateLiveData.observeAsState(HomeState.Loading)

    val coroutineScope = rememberCoroutineScope()

    val homeScrollState: ScrollState = rememberScrollState(0)

    val premiumState = rememberLazyListState()
    val premiumNestedScrollConnection = remember {
        nestedScrollConnection(
            coroutineScope,
            premiumState
        )
    }

    val promotionState = rememberLazyListState()
    val promotionNestedScrollConnection = remember {
        nestedScrollConnection(
            coroutineScope,
            promotionState
        )
    }

    val popularState = rememberLazyListState()
    val popularNestedScrollConnection = remember {
        nestedScrollConnection(
            coroutineScope,
            popularState
        )
    }

    ExpandableToolbar(
        scrollState = homeScrollState,
        searchQuery = "",
        onQueryChange = {},
        locationName = stringResource(com.msaggik.commonui.R.string.yaroslawl)
    ) {
        when (val currentState = homeState) {
            is HomeState.Loading -> {
                CircularProgressIndicator(
                    modifier = Modifier.padding(paddingValues)
                )
            }

            is HomeState.Content -> {
                if (currentState.homeResponse.resultCode == HttpStatus.OK) {
                    HomeBaseContent(
                        homeResponse = currentState.homeResponse.successHomeResponse,
                        homeScrollState = homeScrollState,
                        paddingValues = paddingValues,
                        premiumState = premiumState,
                        premiumNestedScrollConnection = premiumNestedScrollConnection,
                        promotionState = promotionState,
                        promotionNestedScrollConnection = promotionNestedScrollConnection,
                        popularState = popularState,
                        popularNestedScrollConnection = popularNestedScrollConnection
                    )
                } else {
                    ErrorMessage(
                        errorMessage = currentState.homeResponse.resultCode.name,
                        paddingValues = paddingValues
                    )
                }
            }

            is HomeState.Error -> {
                val errorMessage = (homeState as HomeState.Error).errorMessage
                ErrorMessage(
                    errorMessage = errorMessage,
                    paddingValues = paddingValues
                )
            }
        }
    }
}

@Composable
fun ErrorMessage(
    errorMessage: String,
    paddingValues: PaddingValues
) {
    Text(
        text = errorMessage,
        color = MaterialTheme.colorScheme.error,
        modifier = Modifier.padding(paddingValues)
    )
}

private fun nestedScrollConnection(
    coroutineScope: CoroutineScope,
    listState: LazyListState,
) = object : NestedScrollConnection {
    override fun onPreScroll(
        available: Offset,
        source: NestedScrollSource
    ): Offset {
        if (
            available.x < 0
            && available.x >= -1 * SCROLL_THRESHOLD
            && listState.firstVisibleItemIndex != listState.layoutInfo.totalItemsCount - 1
        ) {
            coroutineScope.launch {
                listState.animateScrollToItem(listState.firstVisibleItemIndex + 1)
            }
        } else if (
            available.x > 0
            && available.x <= SCROLL_THRESHOLD
        ) {
            coroutineScope.launch {
                listState.animateScrollToItem(listState.firstVisibleItemIndex)
            }
        }
        return Offset.Zero
    }
}

@Composable
private fun HomeBaseContent(
    homeResponse: SuccessHomeResponse,
    homeScrollState: ScrollState,
    paddingValues: PaddingValues,
    premiumState: LazyListState,
    premiumNestedScrollConnection: NestedScrollConnection,
    promotionState: LazyListState,
    promotionNestedScrollConnection: NestedScrollConnection,
    popularState: LazyListState,
    popularNestedScrollConnection: NestedScrollConnection,
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(homeScrollState)
            .padding(bottom = paddingValues.calculateBottomPadding())
    ) {
        Spacer(Modifier.height(185.dp))
//        Spacer(Modifier.height(HEADER_HEIGHT))
        Column(modifier = Modifier.background(MaterialTheme.colorScheme.background)) {
            // data response
            val title = homeResponse.title
            val image = homeResponse.image
            val catalogCount: String = homeResponse.catalogCount
            val catalogList = homeResponse.blocks.catalog
            val vip = homeResponse.blocks.vip
            val shares = homeResponse.blocks.shares.list
            val example1 = homeResponse.blocks.examples
            val example2 = homeResponse.blocks.examples2

            Log.e("title", "title $title")
            Log.e("title", "image $image")
            Log.e("title", "catalogCount $catalogCount")
            Log.e("title", "catalogList $catalogList")
            Log.e("title", "shares $shares")
            Log.e("title", "vip $vip")
            Log.e("title", "example1 $example1")
            Log.e("title", "example2 $example2")

            CatalogHolder(
                numberCatalog = catalogCount,
                catalogList = catalogList
            )
            PremiumHolder(
                premiumState = premiumState,
                premiumNestedScrollConnection = premiumNestedScrollConnection,
                vipList = vip
            )
            PromotionsHolder(
                promotionState = promotionState,
                promotionNestedScrollConnection = promotionNestedScrollConnection,
                shares = shares
            )
        }
    }
}

@Composable
fun HeaderComponent(
    title: String,
    number: String
) {
    Row(
        modifier = Modifier.padding(16.dp),
        horizontalArrangement = Arrangement.spacedBy(16.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = title,
            style = MaterialTheme.typography.bodyMedium,
            color = MaterialTheme.colorScheme.onSurface,
            fontWeight = FontWeight.Bold
        )
        Text(
            text = number,
            style = MaterialTheme.typography.bodyLarge,
            color = MaterialTheme.colorScheme.tertiary,
            fontWeight = FontWeight.Bold
        )
    }
}

@Composable
private fun CatalogHolder(
    numberCatalog: String,
    catalogList: List<Catalog>
) {
    HeaderComponent(
        title = stringResource(com.msaggik.commonui.R.string.categories),
        number = numberCatalog
    )
    LazyHorizontalGrid(
        modifier = Modifier
            .height(190.dp)
            .background(MaterialTheme.colorScheme.background),
        rows = GridCells.Fixed(2),
        contentPadding = PaddingValues(horizontal = 16.dp),
        horizontalArrangement = Arrangement.spacedBy(8.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        items(catalogList) {
            Box(
                modifier = Modifier
                    .height(91.dp)
                    .width(170.dp)
                    .clip(RoundedCornerShape(12.dp)),
                contentAlignment = Alignment.Center
            ) {
                SubcomposeAsyncImage(
                    modifier = Modifier.fillMaxSize(),
                    contentScale = ContentScale.Crop,
                    model = it.image.origin,
                    error = {
                        Image(
                            contentScale = ContentScale.Crop,
                            painter = painterResource(id = com.msaggik.commonui.R.drawable.catalog_placeholder),
                            contentDescription = stringResource(id = com.msaggik.commonui.R.string.error_load_image),

                            )
                    },
                    contentDescription = stringResource(com.msaggik.commonui.R.string.categories)
                )
                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .background(Color.Black.copy(0.3f))
                )
                Text(
                    modifier = Modifier.width(130.dp),
                    textAlign = TextAlign.Center,
                    text = it.name,
                    style = MaterialTheme.typography.bodyMedium,
                    fontWeight = FontWeight.Bold,
                    color = MaterialTheme.colorScheme.primary,
                    maxLines = 2,
                    overflow = TextOverflow.Ellipsis
                )
            }
        }
    }
}

@Composable
private fun PremiumHolder(
    premiumState: LazyListState,
    premiumNestedScrollConnection: NestedScrollConnection,
    vipList: List<Vip>
) {
    HeaderComponent(
        title = stringResource(com.msaggik.commonui.R.string.premium),
        number = vipList.size.toString()
    )
    LazyColumn(
        state = premiumState,
        modifier = Modifier
            .fillMaxWidth().height(150.dp)
            .nestedScroll(premiumNestedScrollConnection),
        contentPadding = PaddingValues(horizontal = 16.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        items(vipList) {

        }
    }
}

@Composable
private fun PromotionsHolder(
    promotionState: LazyListState,
    promotionNestedScrollConnection: NestedScrollConnection,
    shares: List<Share>,
) {
    Row(
        modifier = Modifier.fillMaxWidth().padding(end = 16.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        HeaderComponent(
            title = stringResource(com.msaggik.commonui.R.string.promotions),
            number = shares.size.toString()
        )
        Text(
            text = stringResource(com.msaggik.commonui.R.string.more_big),
            style = MaterialTheme.typography.bodyLarge,
            color = MaterialTheme.colorScheme.onTertiary,
            fontWeight = FontWeight.Bold
        )
    }

    LazyRow(
        state = promotionState,
        modifier = Modifier
            .fillMaxWidth()
            .nestedScroll(promotionNestedScrollConnection),
        contentPadding = PaddingValues(horizontal = 16.dp),
        horizontalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        items(shares) {
            Column(
                modifier = Modifier
                    .fillParentMaxWidth(0.99f)
                    .clip(RoundedCornerShape(16.dp))
                    .background(color = MaterialTheme.colorScheme.surface)
            ) {
                Box(
                    modifier = Modifier
                        .height(160.dp)
                        .fillMaxWidth()
                ) {
                    AsyncImage(
                        modifier = Modifier.fillMaxSize(),
                        model = it.companyImage,
                        contentDescription = stringResource(com.msaggik.commonui.R.string.company_image),
                        contentScale = ContentScale.Crop
                    )
                    Box(
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(16.dp)
                    ) {
                        Text(
                            modifier = Modifier
                                .clip(RoundedCornerShape(16.dp))
                                .background(MaterialTheme.colorScheme.surfaceVariant)
                                .padding(horizontal = 12.dp, vertical = 4.dp),
                            text = stringResource(com.msaggik.commonui.R.string.n_percent, it.discountValue),
                            style = MaterialTheme.typography.bodyMedium,
                            fontWeight = FontWeight.SemiBold,
                            color = MaterialTheme.colorScheme.onSurfaceVariant
                        )
                        Text(
                            modifier = Modifier
                                .align(Alignment.BottomStart)
                                .clip(RoundedCornerShape(16.dp))
                                .background(
                                    MaterialTheme.colorScheme.surface.copy(
                                        0.55f
                                    )
                                )
                                .padding(horizontal = 12.dp, vertical = 4.dp),
                            style = MaterialTheme.typography.bodySmall,
                            color = MaterialTheme.colorScheme.onSurface,
                            text = buildAnnotatedString {
                                append(stringResource(com.msaggik.commonui.R.string.until))
                                withStyle(SpanStyle(fontWeight = FontWeight.Bold)) {
                                    append(
                                        Utils.formatDate(
                                            context = LocalContext.current,
                                            locale = Locale.getDefault(),
                                            inputDate = it.publicTimeStop
                                        )
                                    )
                                }
                            },
                        )
                        Row(
                            modifier = Modifier
                                .align(Alignment.BottomEnd)
                                .clip(RoundedCornerShape(16.dp))
                                .background(
                                    MaterialTheme.colorScheme.surface.copy(
                                        0.55f
                                    )
                                )
                                .padding(horizontal = 12.dp, vertical = 4.dp),
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.spacedBy(6.dp)
                        ) {
                            Icon(
                                modifier = Modifier.size(16.dp),
                                painter = painterResource(id = com.msaggik.commonui.R.drawable.ic_view),
                                contentDescription = stringResource(com.msaggik.commonui.R.string.view),
                                tint = MaterialTheme.colorScheme.onSurface
                            )
                            Text(
                                text = it.view,
                                style = MaterialTheme.typography.bodySmall,
                                color = MaterialTheme.colorScheme.onSurface,
                            )
                        }
                    }
                }
                Column(modifier = Modifier.padding(16.dp)) {
                    Text(
                        text = it.name,
                        style = MaterialTheme.typography.bodyLarge,
                        color = MaterialTheme.colorScheme.onSurface,
                        maxLines = 2,
                        fontWeight = FontWeight.Bold,
                        overflow = TextOverflow.Ellipsis
                    )
                    Spacer(modifier = Modifier.height(16.dp))
                    Row(
                        horizontalArrangement = Arrangement.spacedBy(16.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        AsyncImage(
                            modifier = Modifier
                                .size(50.dp)
                                .clip(CircleShape),
                            contentScale = ContentScale.Crop,
                            model = it.icon,
                            contentDescription = stringResource(com.msaggik.commonui.R.string.avatar)
                        )
                        Column {
                            Text(
                                text = it.companyName,
                                style = MaterialTheme.typography.bodyLarge,
                                color = MaterialTheme.colorScheme.onSurface,
                                maxLines = 1,
                                fontWeight = FontWeight.Bold,
                                overflow = TextOverflow.Ellipsis
                            )
                            Text(
                                text = it.companyStreet,
                                style = MaterialTheme.typography.bodySmall,
                                color = MaterialTheme.colorScheme.tertiary,
                                maxLines = 1,
                                fontWeight = FontWeight.Bold,
                                overflow = TextOverflow.Ellipsis
                            )
                        }
                    }
                }
            }
        }
    }
}
