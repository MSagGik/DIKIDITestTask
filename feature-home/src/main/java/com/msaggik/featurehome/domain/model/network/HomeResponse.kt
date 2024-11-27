package com.msaggik.featurehome.domain.model.network

import com.msaggik.commonutils.network.HttpStatus
import com.msaggik.commonutils.network.HttpResponse
import com.msaggik.featurehome.domain.model.network.error.ErrorHomeResponse
import com.msaggik.featurehome.domain.model.network.success.SuccessHomeResponse

@Suppress("DataClassShouldBeImmutable")
data class HomeResponse(
    override var resultCode: HttpStatus,
    val errorHomeResponse: ErrorHomeResponse,
    val successHomeResponse: SuccessHomeResponse
) : HttpResponse
