package com.msaggik.featurehome.presentation.ui

import android.Manifest
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.ScrollState
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.rememberScrollState
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.input.nestedscroll.NestedScrollConnection
import androidx.compose.ui.input.nestedscroll.NestedScrollSource
import androidx.compose.ui.res.stringResource
import com.msaggik.commonutils.network.HttpStatus
import com.msaggik.featurehome.presentation.viewmodel.HomeViewModel
import com.msaggik.featurehome.presentation.viewmodel.state.HomeState
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch
import org.koin.androidx.compose.koinViewModel
import com.msaggik.commonui.R
import com.msaggik.featurehome.presentation.ui.holders.basecontent.HomeBaseContent
import com.msaggik.featurehome.presentation.ui.holders.basecontent.common.ErrorMessage
import com.msaggik.featurehome.presentation.ui.holders.toolbar.ExpandableToolbar

private const val SCROLL_THRESHOLD = 30

@Composable
fun HomeCompose(
    paddingValues: PaddingValues
) {
    val homeViewModel: HomeViewModel = koinViewModel()
    val homeState by homeViewModel.homeStateLiveData.observeAsState(HomeState.Loading)

    val permissionLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.RequestPermission(),
        onResult = { isGranted ->
            if (isGranted) {
                homeViewModel.requestLocation()
            } else {
                homeViewModel.getHomeInfoCityById()
            }
        }
    )

    LaunchedEffect(Unit) {
        permissionLauncher.launch(Manifest.permission.ACCESS_FINE_LOCATION)
    }

    val coroutineScope = rememberCoroutineScope()

    val homeScrollState: ScrollState = rememberScrollState()

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
        locationName = stringResource(R.string.yaroslawl)
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
