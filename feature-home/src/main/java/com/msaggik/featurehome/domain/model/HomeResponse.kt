package com.msaggik.featurehome.domain.model

import com.google.gson.annotations.SerializedName
import com.msaggik.commonutils.network.HttpStatus
import com.msaggik.commonutils.network.Response
import com.msaggik.featurehome.domain.model.error.ErrorHomeResponse
import com.msaggik.featurehome.domain.model.success.SuccessHomeResponse

data class HomeResponse (
    override var resultCode: HttpStatus,
    @SerializedName("error")
    val errorHomeResponse: ErrorHomeResponse,
    @SerializedName("data")
    val successHomeResponse: SuccessHomeResponse
) : Response
