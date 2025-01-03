package com.msaggik.datanetwork.api.dto.response.success.blocks.catalog.currency

import com.google.gson.annotations.SerializedName

data class CurrencyDto(
    @SerializedName("id")
    val id: String,
    @SerializedName("title")
    val title: String,
    @SerializedName("abbr")
    val abbr: String
)
