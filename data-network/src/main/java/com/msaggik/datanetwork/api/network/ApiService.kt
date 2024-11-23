package com.msaggik.datanetwork.api.network

import com.msaggik.datanetwork.api.dto.HomeResponseDto
import com.msaggik.dikiditesttask.BuildConfig
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.POST
import retrofit2.http.Path

private const val DEFAULT_ID_CITY = 468902
private const val API_TOKEN = BuildConfig.API_ACCESS_TOKEN
internal interface ApiService {

    @GET("home/info")
    suspend fun getHomeInfo(
        @Header("Authorization") token: String = API_TOKEN
    ): HomeResponseDto

    @POST("home/info/{city_id}")
    suspend fun getHomeInfoCityById(
        @Header("Authorization") token: String = API_TOKEN,
        @Path("city_id") cityId: Int = DEFAULT_ID_CITY,
    ): HomeResponseDto

    @POST("home/info/{lat}/{lng}")
    suspend fun getHomeInfoCityByLocation(
        @Header("Authorization") token: String = API_TOKEN,
        @Path("lat") lat: String,
        @Path("lng") lng: String
    ): HomeResponseDto
}
