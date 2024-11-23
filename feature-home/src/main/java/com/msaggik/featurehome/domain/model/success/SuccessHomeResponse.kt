package com.msaggik.featurehome.domain.model.success

import com.google.gson.annotations.SerializedName
import com.msaggik.featurehome.domain.model.success.blocks.Blocks

data class SuccessHomeResponse(
    @SerializedName("title")
    val title: String,
    @SerializedName("image")
    val image: String,
    @SerializedName("catalog_count")
    val catalogCount: String,
    @SerializedName("blocks")
    val blocks: Blocks,
    @SerializedName("order")
    val order: List<String>
)
