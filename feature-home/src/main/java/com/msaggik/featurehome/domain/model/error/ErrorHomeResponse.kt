package com.msaggik.featurehome.domain.model.error

import com.google.gson.annotations.SerializedName

data class ErrorHomeResponse(
    @SerializedName("code")
    val code: Int,
    @SerializedName("message")
    val message: String?
)
