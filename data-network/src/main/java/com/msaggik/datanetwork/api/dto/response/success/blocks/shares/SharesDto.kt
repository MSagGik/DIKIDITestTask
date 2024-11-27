package com.msaggik.datanetwork.api.dto.response.success.blocks.shares

import com.google.gson.annotations.SerializedName

data class SharesDto(
    @SerializedName("list")
    val list: List<ShareDto>,
    @SerializedName("count")
    val count: String
)
