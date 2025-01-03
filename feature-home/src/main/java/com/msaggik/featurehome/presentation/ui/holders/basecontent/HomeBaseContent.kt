package com.msaggik.featurehome.presentation.ui.holders.basecontent

import androidx.compose.foundation.ScrollState
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.nestedscroll.NestedScrollConnection
import androidx.compose.ui.unit.dp
import com.msaggik.featurehome.domain.model.network.success.SuccessHomeResponse
import com.msaggik.featurehome.presentation.ui.holders.basecontent.holders.CatalogHolder
import com.msaggik.featurehome.presentation.ui.holders.basecontent.holders.ExamplesOfWorksHolder
import com.msaggik.featurehome.presentation.ui.holders.basecontent.holders.PopularHolder
import com.msaggik.featurehome.presentation.ui.holders.basecontent.holders.PremiumHolder
import com.msaggik.featurehome.presentation.ui.holders.basecontent.holders.PromotionsHolder

private val TOOLBAR_HEIGHT = 210.dp

@Composable
internal fun HomeBaseContent(
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
            .verticalScroll(homeScrollState)
            .padding(bottom = paddingValues.calculateBottomPadding())
            .fillMaxSize()
    ) {
        Spacer(Modifier.height(TOOLBAR_HEIGHT))
        // data response
        val catalogCount: String = homeResponse.catalogCount
        val catalogList = homeResponse.blocks.catalog
        val vip = homeResponse.blocks.vip
        val shares = homeResponse.blocks.shares.list
        val example1 = homeResponse.blocks.examples
        val example2 = homeResponse.blocks.examples2
        val exampleList = mutableListOf<String>()
        if (example1.isNotEmpty()) exampleList.add(example1)
        if (example2.isNotEmpty()) exampleList.add(example2)

        if (catalogList.isNotEmpty()) {
            CatalogHolder(
                numberCatalog = catalogCount,
                catalogList = catalogList
            )
        }
        if (vip.isNotEmpty()) {
            PremiumHolder(
                premiumState = premiumState,
                premiumNestedScrollConnection = premiumNestedScrollConnection,
                vipList = vip
            )
        }
        if (shares.isNotEmpty()) {
            PromotionsHolder(
                promotionState = promotionState,
                promotionNestedScrollConnection = promotionNestedScrollConnection,
                shares = shares
            )
        }

        if (catalogList.isNotEmpty()) {
            PopularHolder(
                popularState = popularState,
                popularNestedScrollConnection = popularNestedScrollConnection,
                catalogList = catalogList.sortedByDescending { it.rating }
            )
        }

        if (exampleList.isNotEmpty()) {
            ExamplesOfWorksHolder(examplesOfWorksUri = exampleList)
        }

        Spacer(Modifier.height(19.dp))
    }
}
