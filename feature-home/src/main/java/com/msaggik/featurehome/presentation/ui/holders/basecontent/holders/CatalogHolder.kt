package com.msaggik.featurehome.presentation.ui.holders.basecontent.holders

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyHorizontalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import coil.compose.SubcomposeAsyncImage
import com.msaggik.featurehome.domain.model.network.success.blocks.catalog.Catalog
import com.msaggik.featurehome.presentation.ui.holders.basecontent.common.Header

private const val ALPHA_COLOR = 0.3f
@Composable
internal fun CatalogHolder(
    numberCatalog: String,
    catalogList: List<Catalog>
) {
    Header(
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
                    model = it.image.thumb,
                    error = {
                        Image(
                            contentScale = ContentScale.Crop,
                            painter = painterResource(id = com.msaggik.commonui.R.drawable.catalog_placeholder),
                            contentDescription = stringResource(id = com.msaggik.commonui.R.string.error_load_image)
                        )
                    },
                    contentDescription = stringResource(com.msaggik.commonui.R.string.categories)
                )
                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .background(Color.Black.copy(ALPHA_COLOR))
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

