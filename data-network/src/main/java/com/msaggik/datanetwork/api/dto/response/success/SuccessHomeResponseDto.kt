package com.msaggik.datanetwork.api.dto.response.success

import com.google.gson.annotations.SerializedName
import com.msaggik.datanetwork.api.dto.response.success.blocks.BlocksDto

data class SuccessHomeResponseDto(
    @SerializedName("title")
    val title: String,
    @SerializedName("image")
    val image: String,
    @SerializedName("catalog_count")
    val catalogCount: String,
    @SerializedName("blocks")
    val blocks: BlocksDto,
    @SerializedName("order")
    val order: List<String>
)
