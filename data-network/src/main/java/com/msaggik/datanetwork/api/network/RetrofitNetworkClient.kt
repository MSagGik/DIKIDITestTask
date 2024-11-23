package com.msaggik.datanetwork.api.network

import android.content.Context
import android.util.Log
import com.msaggik.commonutils.all.Utils
import com.msaggik.commonutils.network.HttpStatus
import com.msaggik.commonutils.network.Response
import com.msaggik.commonutils.network.isConnected
import com.msaggik.datanetwork.api.NetworkClient
import com.msaggik.datanetwork.api.dto.HomeResponseDto
import com.msaggik.datanetwork.api.dto.request.HomeRequestByIdCityDto
import com.msaggik.datanetwork.api.dto.request.HomeRequestByIdLocationDto
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import retrofit2.HttpException

private const val TAG = "RetrofitNetworkClient"
private const val HTTP_CODE_400 = 400
private const val HTTP_CODE_401 = 401
private const val HTTP_CODE_403 = 403
private const val HTTP_CODE_404 = 404
private const val HTTP_CODE_500 = 500
private const val HTTP_CODE_599 = 599

internal class RetrofitNetworkClient(
    private val context: Context,
    private val apiService: ApiService,
) : NetworkClient {

    override suspend fun doRequest(dto: Any): Response {
        Log.d(TAG, "Starting request to server")
        if (!context.isConnected()) {
            return object : Response {
                override var resultCode = HttpStatus.NO_INTERNET
            }
        }
        return withContext(Dispatchers.IO) {
            runCatching {
                when (dto) {
                    is HomeResponseDto -> getHomeInfoRequest()
                    is HomeRequestByIdCityDto -> getHomeInfoCityById(dto)
                    is HomeRequestByIdLocationDto -> getHomeInfoCityByLocation(dto)
                    else -> throw IllegalArgumentException("Error is ${dto::class.qualifiedName}")
                }
            }.fold(
                onSuccess = { response ->
                    response.apply { resultCode = HttpStatus.OK }
                },
                onFailure = { e ->
                    Utils.outputStackTrace(TAG, e)
                    val modCodeHTTP = modifiedCodeHTTP(e)
                    object : Response {
                        override var resultCode = modCodeHTTP
                    }
                }
            )
        }
    }

    private fun modifiedCodeHTTP(exception: Throwable): HttpStatus {
        return if (exception is HttpException) {
            when (exception.code()) {
                HTTP_CODE_400 -> {
                    HttpStatus.CLIENT_ERROR_BAD_REQUEST
                }
                HTTP_CODE_401 -> {
                    HttpStatus.CLIENT_ERROR_UNAUTHORIZED
                }
                HTTP_CODE_403 -> {
                    HttpStatus.CLIENT_ERROR_FORBIDDEN
                }
                HTTP_CODE_404 -> {
                    HttpStatus.CLIENT_ERROR_NOT_FOUND
                }
                in HTTP_CODE_500..HTTP_CODE_599 -> {
                    HttpStatus.SERVER_ERROR
                }
                else -> {
                    HttpStatus.CLIENT_ERROR
                }
            }
        } else {
            HttpStatus.CLIENT_ERROR
        }
    }

    private suspend fun getHomeInfoRequest(): Response {
        val homeInfo = apiService.getHomeInfo()
        Log.d(TAG, homeInfo.toString())
        return homeInfo
    }

    private suspend fun getHomeInfoCityById(dto: HomeRequestByIdCityDto): Response {
        val homeInfo = apiService.getHomeInfoCityById(cityId = dto.cityId)
        Log.d(TAG,  homeInfo.toString())
        Log.d(TAG, "cityId ${dto.cityId}")
        return homeInfo
    }

    private suspend fun getHomeInfoCityByLocation(dto: HomeRequestByIdLocationDto): Response {
        val homeInfo = apiService.getHomeInfoCityByLocation(
            lat = dto.lat,
            lng = dto.lng
        )
        Log.d(TAG,  homeInfo.toString())
        Log.d(TAG, "lat ${dto.lat}, lng ${dto.lng}")
        return homeInfo
    }
}
