package com.msaggik.featurehome.domain.model.success.common

import com.google.gson.annotations.SerializedName

data class Image(
    @SerializedName("thumb")
    val thumb: String,
    @SerializedName("origin")
    val origin: String
)
