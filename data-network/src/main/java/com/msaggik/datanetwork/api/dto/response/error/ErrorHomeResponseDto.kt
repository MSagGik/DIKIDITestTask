package com.msaggik.datanetwork.api.dto.response.error

import com.google.gson.annotations.SerializedName

data class ErrorHomeResponseDto(
    @SerializedName("code")
    val code: Int,
    @SerializedName("message")
    val message: String?
)
