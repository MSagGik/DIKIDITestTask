package com.msaggik.featurehome.presentation.ui.holders.toolbar.components

import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp

@Composable
internal fun RowScope.SearchEditText(
    searchQuery: String,
    onQueryChange: (String)->Unit
) {
    val interactionSource = remember { MutableInteractionSource() }
    var searchQueryExample by remember { mutableStateOf("") }

    TextField(
        modifier = Modifier.weight(1f).height(50.dp),
        value = searchQueryExample,
        onValueChange = { newText ->
            searchQueryExample = newText
        },
        singleLine = true,
        enabled = true,
        shape = RoundedCornerShape(16.dp),
        interactionSource = interactionSource,
        placeholder = {
            Text(
                modifier = Modifier.weight(1f),
                text = stringResource(com.msaggik.commonui.R.string.search_hint),
                maxLines = 1,
                overflow = TextOverflow.Ellipsis,
                style = MaterialTheme.typography.bodyMedium,
                color = MaterialTheme.colorScheme.secondary,
                fontWeight = FontWeight.Medium,
            )
        },
        textStyle = TextStyle(color = MaterialTheme.colorScheme.onSecondary),
        colors = TextFieldDefaults.colors(
            focusedTextColor = MaterialTheme.colorScheme.secondary,
            unfocusedTextColor = MaterialTheme.colorScheme.secondary,
            disabledPlaceholderColor = MaterialTheme.colorScheme.onSecondary,
            focusedPlaceholderColor = MaterialTheme.colorScheme.onSecondary,
            unfocusedPlaceholderColor = MaterialTheme.colorScheme.onSecondary,
            focusedContainerColor = MaterialTheme.colorScheme.surface,
            unfocusedContainerColor = MaterialTheme.colorScheme.surface,
            focusedIndicatorColor = Color.Transparent,
            unfocusedIndicatorColor = Color.Transparent,
            cursorColor = MaterialTheme.colorScheme.onTertiary
        ),
        leadingIcon = {
            Icon(
                modifier = Modifier.size(25.dp).padding(start = 5.dp),
                painter = painterResource(id = com.msaggik.commonui.R.drawable.ic_search),
                contentDescription = stringResource(com.msaggik.commonui.R.string.search),
                tint = MaterialTheme.colorScheme.secondary
            )
        },
    )
}
