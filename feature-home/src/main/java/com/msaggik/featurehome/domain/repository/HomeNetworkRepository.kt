package com.msaggik.featurehome.domain.repository

import com.msaggik.commonutils.all.Response
import com.msaggik.featurehome.domain.model.network.HomeResponse
import kotlinx.coroutines.flow.Flow

interface HomeNetworkRepository {

    fun getHomeInfo(): Flow<Response<HomeResponse>>

    fun getHomeInfoCityById(cityId: Int): Flow<Response<HomeResponse>>

    fun getHomeInfoCityByLocation(
        lat: String,
        lng: String
    ): Flow<Response<HomeResponse>>
}
