package com.msaggik.featurehome.domain.usecase

import com.msaggik.featurehome.domain.model.HomeResponse
import kotlinx.coroutines.flow.Flow

interface HomeInteractor {

    fun getHomeInfo(): Flow<Pair<HomeResponse?, String?>>

    fun getHomeInfoCityById(cityId: Int): Flow<Pair<HomeResponse?, String?>>

    fun getHomeInfoCityByLocation(
        lat: String,
        lng: String
    ): Flow<Pair<HomeResponse?, String?>>
}
