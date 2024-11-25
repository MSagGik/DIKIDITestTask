package com.msaggik.featurehome.presentation.ui

import androidx.compose.foundation.ScrollState
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.input.nestedscroll.NestedScrollConnection
import androidx.compose.ui.input.nestedscroll.NestedScrollSource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.msaggik.commonutils.network.HttpStatus
import com.msaggik.featurehome.domain.model.success.SuccessHomeResponse
import com.msaggik.featurehome.presentation.viewmodel.HomeViewModel
import com.msaggik.featurehome.presentation.viewmodel.state.HomeState
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch
import org.koin.androidx.compose.koinViewModel

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

    val sharesState = rememberLazyListState()
    val sharesNestedScrollConnection = remember {
        nestedScrollConnection(
            coroutineScope,
            sharesState
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
                        currentState.homeResponse.successHomeResponse,
                        homeScrollState,
                        paddingValues,
                        sharesState,
                        sharesNestedScrollConnection,
                        popularState,
                        popularNestedScrollConnection
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
    mainScrollState: ScrollState,
    paddingValues: PaddingValues,
    sharesState: LazyListState,
    sharesNestedScrollConnection: NestedScrollConnection,
    popularState: LazyListState,
    popularNestedScrollConnection: NestedScrollConnection,
) {
    Column(
        modifier = Modifier
            .verticalScroll(mainScrollState)
            .padding(bottom = paddingValues.calculateBottomPadding())
    ) {
        Spacer(Modifier.height(HEADER_HEIGHT))
        Column(modifier = Modifier.background(MaterialTheme.colorScheme.primary)) {
            // data response
            val title = homeResponse.title
            val image = homeResponse.image
            val catalogCount: String = homeResponse.catalogCount
            val categories = homeResponse.blocks.catalog
            val shares = homeResponse.blocks.shares
            val vip = homeResponse.blocks.vip
            val example1 = homeResponse.blocks.examples
            val example2 = homeResponse.blocks.examples2

        }
    }
}
