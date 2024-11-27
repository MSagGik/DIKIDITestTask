package com.msaggik.featurehome.domain.model.network.success

import com.msaggik.featurehome.domain.model.network.success.blocks.Blocks

data class SuccessHomeResponse(
    val title: String,
    val image: String,
    val catalogCount: String,
    val blocks: Blocks,
    val order: List<String>
)
