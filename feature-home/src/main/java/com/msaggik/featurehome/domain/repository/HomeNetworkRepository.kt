package com.msaggik.featurehome.domain.repository

import com.msaggik.commonutils.all.Resource
import com.msaggik.featurehome.domain.model.HomeResponse
import kotlinx.coroutines.flow.Flow

interface HomeNetworkRepository {

    fun getHomeInfo(): Flow<Resource<HomeResponse>>

    fun getHomeInfoCityById(cityId: Int): Flow<Resource<HomeResponse>>

    fun getHomeInfoCityByLocation(
        lat: String,
        lng: String
    ): Flow<Resource<HomeResponse>>
}
