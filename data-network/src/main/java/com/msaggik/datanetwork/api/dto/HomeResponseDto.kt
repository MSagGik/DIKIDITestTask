package com.msaggik.datanetwork.api.dto

import com.google.gson.annotations.SerializedName
import com.msaggik.commonutils.network.HttpStatus
import com.msaggik.commonutils.network.Response
import com.msaggik.datanetwork.api.dto.response.error.ErrorHomeResponseDto
import com.msaggik.datanetwork.api.dto.response.success.SuccessHomeResponseDto

@Suppress("DataClassShouldBeImmutable")
data class HomeResponseDto(
    override var resultCode: HttpStatus,
    @SerializedName("error")
    val errorHomeResponseDto: ErrorHomeResponseDto,
    @SerializedName("data")
    val successHomeResponseDto: SuccessHomeResponseDto
) : Response
