package com.msaggik.featurehome.presentation.ui.holders.basecontent.holders

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
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.input.nestedscroll.NestedScrollConnection
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.msaggik.commonutils.all.Utils
import com.msaggik.featurehome.domain.model.network.success.blocks.shares.Share
import com.msaggik.featurehome.presentation.ui.holders.basecontent.common.Header
import java.util.Locale

private const val PARAMETER_WIDTH = 0.99f
private const val PARAMETER_ALPHA = 0.55f

@Composable
internal fun PromotionsHolder(
    promotionState: LazyListState,
    promotionNestedScrollConnection: NestedScrollConnection,
    shares: List<Share>,
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(end = 16.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Header(
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
                    .fillParentMaxWidth(PARAMETER_WIDTH)
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
                                        PARAMETER_ALPHA
                                    )
                                )
                                .padding(horizontal = 12.dp, vertical = 4.dp),
                            style = MaterialTheme.typography.bodySmall,
                            color = MaterialTheme.colorScheme.onSurface,
                            text = buildAnnotatedString {
                                append(stringResource(com.msaggik.commonui.R.string.until) + " ")
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
                                        PARAMETER_ALPHA
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
