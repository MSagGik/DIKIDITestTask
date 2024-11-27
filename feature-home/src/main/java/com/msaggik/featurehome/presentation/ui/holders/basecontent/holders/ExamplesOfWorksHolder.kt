package com.msaggik.featurehome.presentation.ui.holders.basecontent.holders

import androidx.compose.animation.Crossfade
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.msaggik.featurehome.presentation.ui.holders.basecontent.common.Header
import kotlinx.coroutines.delay

private const val DELAY_ANIMATION = 5000L

@Composable
internal fun ExamplesOfWorksHolder(
    examplesOfWorksUri: List<String>
) {
    var currentImageIndex by remember { mutableIntStateOf(0) }

    Header(
        title = stringResource(com.msaggik.commonui.R.string.examples_of_works),
    )

    Crossfade(
        targetState = examplesOfWorksUri[currentImageIndex],
        label = ""
    ) { imageUri ->
        AsyncImage(
            modifier = Modifier
                .padding(horizontal = 16.dp)
                .fillMaxWidth()
                .height(250.dp)
                .clip(RoundedCornerShape(16.dp)),
            model = ImageRequest.Builder(LocalContext.current)
                .data(imageUri)
                .crossfade(true)
                .build(),
            contentDescription = stringResource(com.msaggik.commonui.R.string.examples_of_works),
            contentScale = ContentScale.Crop
        )
    }

    Spacer(modifier = Modifier.height(16.dp))
    Text(
        modifier = Modifier
            .padding(horizontal = 16.dp)
            .fillMaxWidth()
            .border(
                1.dp, MaterialTheme.colorScheme.onTertiary, shape = RoundedCornerShape(16.dp)
            )
            .padding(horizontal = 12.dp, vertical = 10.dp),
        text = stringResource(com.msaggik.commonui.R.string.look),
        textAlign = TextAlign.Center,
        style = MaterialTheme.typography.bodySmall,
        fontWeight = FontWeight.Bold,
        color = MaterialTheme.colorScheme.onTertiary
    )
    LaunchedEffect(Unit) {
        while (true) {
            delay(DELAY_ANIMATION)
            currentImageIndex = (currentImageIndex + 1) % examplesOfWorksUri.size
        }
    }
}
