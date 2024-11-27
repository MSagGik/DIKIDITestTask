package com.msaggik.featurehome.domain.usecase

import com.msaggik.featurehome.domain.model.location.Location
import com.msaggik.featurehome.domain.model.locationsp.LocationSp
import com.msaggik.featurehome.domain.model.network.HomeResponse
import kotlinx.coroutines.flow.Flow

interface HomeInteractor {

    fun getHomeInfo(): Flow<Pair<HomeResponse?, String?>>

    fun getHomeInfoCityById(cityId: Int): Flow<Pair<HomeResponse?, String?>>

    fun getHomeInfoCityByLocation(
        lat: String,
        lng: String
    ): Flow<Pair<HomeResponse?, String?>>

    suspend fun getLocation(): Pair<Location?, String?>

    suspend fun setLastCoordinate(location: LocationSp): Int
    suspend fun getLastCoordinate(): LocationSp?
    suspend fun deleteLastCoordinate(): Int
}
