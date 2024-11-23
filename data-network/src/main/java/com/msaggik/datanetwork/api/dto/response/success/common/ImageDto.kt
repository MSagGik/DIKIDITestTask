package com.msaggik.datanetwork.api.dto.response.success.common

import com.google.gson.annotations.SerializedName

data class ImageDto(
    @SerializedName("thumb")
    val thumb: String,
    @SerializedName("origin")
    val origin: String
)
