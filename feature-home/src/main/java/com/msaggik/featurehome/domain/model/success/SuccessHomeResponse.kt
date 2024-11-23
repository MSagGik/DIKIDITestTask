package com.msaggik.featurehome.domain.model.success

import com.msaggik.featurehome.domain.model.success.blocks.Blocks

data class SuccessHomeResponse(
    val title: String,
    val image: String,
    val catalogCount: String,
    val blocks: Blocks,
    val order: List<String>
)
