package com.msaggik.featurehome.domain.model

import com.msaggik.commonutils.network.HttpStatus
import com.msaggik.commonutils.network.Response
import com.msaggik.featurehome.domain.model.error.ErrorHomeResponse
import com.msaggik.featurehome.domain.model.success.SuccessHomeResponse

@Suppress("DataClassShouldBeImmutable")
data class HomeResponse(
    override var resultCode: HttpStatus,
    val errorHomeResponse: ErrorHomeResponse,
    val successHomeResponse: SuccessHomeResponse
) : Response
