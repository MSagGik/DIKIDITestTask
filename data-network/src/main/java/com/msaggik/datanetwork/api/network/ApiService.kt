package com.msaggik.datanetwork.api.network

import com.msaggik.datanetwork.BuildConfig
import com.msaggik.datanetwork.api.dto.HomeResponseDto
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.POST
import retrofit2.http.Query

private const val API_TOKEN = BuildConfig.API_ACCESS_TOKEN
internal interface ApiService {

    @GET("home/info")
    suspend fun getHomeInfo(
        @Header("Authorization") token: String = API_TOKEN
    ): HomeResponseDto

    @POST("home/info/")
    suspend fun getHomeInfoCityById(
        @Header("Authorization") token: String = API_TOKEN,
        @Query("city_id") cityId: Int,
    ): HomeResponseDto

    @POST("home/info/")
    suspend fun getHomeInfoCityByLocation(
        @Header("Authorization") token: String = API_TOKEN,
        @Query("lat") lat: String,
        @Query("lng") lng: String
    ): HomeResponseDto
}
