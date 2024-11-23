package com.msaggik.datanetwork.api

import com.msaggik.commonutils.network.Response

interface NetworkClient {
    suspend fun doRequest(dto: Any): Response
}
