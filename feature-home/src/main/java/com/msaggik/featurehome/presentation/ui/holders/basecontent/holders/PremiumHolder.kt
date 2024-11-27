package com.msaggik.featurehome.presentation.ui.holders.basecontent.holders

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.input.nestedscroll.NestedScrollConnection
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.msaggik.featurehome.domain.model.network.success.blocks.vip.Vip
import com.msaggik.featurehome.presentation.ui.holders.basecontent.common.Header

@Composable
internal fun PremiumHolder(
    premiumState: LazyListState,
    premiumNestedScrollConnection: NestedScrollConnection,
    vipList: List<Vip>
) {
    Header(
        title = stringResource(com.msaggik.commonui.R.string.premium),
        number = vipList.size.toString()
    )
    LazyColumn(
        state = premiumState,
        modifier = Modifier
            .heightIn(min = 0.dp, max = 500.dp)
            .fillMaxWidth()
            .padding(horizontal = 16.dp)
            .nestedScroll(premiumNestedScrollConnection)
            .clip(RoundedCornerShape(16.dp))
            .background(MaterialTheme.colorScheme.surface),
        contentPadding = PaddingValues(16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        items(vipList) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically
            ) {
                AsyncImage(
                    modifier = Modifier
                        .size(60.dp)
                        .clip(RoundedCornerShape(16.dp)),
                    contentScale = ContentScale.Crop,
                    model = it.image.thumb,
                    contentDescription = stringResource(id = com.msaggik.commonui.R.string.logo)
                )
                Column(
                    modifier = Modifier.weight(1.5f)
                ) {
                    Text(
                        modifier = Modifier.padding(horizontal = 14.dp),
                        text = it.name,
                        style = MaterialTheme.typography.bodyMedium,
                        color = MaterialTheme.colorScheme.onSurface,
                        maxLines = 2,
                        fontWeight = FontWeight.Bold,
                        overflow = TextOverflow.Ellipsis
                    )
                    Text(
                        modifier = Modifier.padding(horizontal = 14.dp),
                        text = it.categories.joinToString(),
                        style = MaterialTheme.typography.bodySmall,
                        color = MaterialTheme.colorScheme.onSurface,
                        maxLines = 2,
                        overflow = TextOverflow.Ellipsis
                    )
                }
                Text(
                    modifier = Modifier
                        .weight(1f)
                        .border(
                            1.dp,
                            MaterialTheme.colorScheme.onTertiary,
                            shape = RoundedCornerShape(12.dp)
                        )
                        .padding(
                            horizontal = 12.dp,
                            vertical = 10.dp
                        ),
                    text = stringResource(com.msaggik.commonui.R.string.enroll),
                    style = MaterialTheme.typography.bodySmall,
                    color = MaterialTheme.colorScheme.onTertiary,
                    fontWeight = FontWeight.Bold,
                    textAlign = TextAlign.Center
                )
            }
            if (vipList.indexOf(it) < vipList.size - 1) {
                HorizontalDivider(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 16.dp),
                    thickness = 0.5.dp,
                    color = MaterialTheme.colorScheme.tertiary.copy( alpha = 0.17f )
                )
            }
        }
    }
}
