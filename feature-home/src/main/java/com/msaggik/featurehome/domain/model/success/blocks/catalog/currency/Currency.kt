package com.msaggik.featurehome.domain.model.success.blocks.catalog.currency

import com.google.gson.annotations.SerializedName

data class Currency(
    @SerializedName("id")
    val id: String,
    @SerializedName("title")
    val title: String,
    @SerializedName("abbr")
    val abbr: String
)
