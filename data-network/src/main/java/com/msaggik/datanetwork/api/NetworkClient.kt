package com.msaggik.datanetwork.api

import com.msaggik.commonutils.network.HttpResponse

interface NetworkClient {
    suspend fun doRequest(dto: Any): HttpResponse
}
