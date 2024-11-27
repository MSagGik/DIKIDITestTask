package com.msaggik.featurehome.domain.usecase.impl

import com.msaggik.commonutils.all.Response
import com.msaggik.featurehome.domain.model.location.Location
import com.msaggik.featurehome.domain.model.locationsp.LocationSp
import com.msaggik.featurehome.domain.model.network.HomeResponse
import com.msaggik.featurehome.domain.repository.HomeNetworkRepository
import com.msaggik.featurehome.domain.repository.LocationRepository
import com.msaggik.featurehome.domain.repository.LocationRepositorySp
import com.msaggik.featurehome.domain.usecase.HomeInteractor
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class HomeInteractorImpl(
    private val homeNetworkRepository: HomeNetworkRepository,
    private val locationRepository: LocationRepository,
    private val locationRepositorySp: LocationRepositorySp
) : HomeInteractor {

    override fun getHomeInfo(): Flow<Pair<HomeResponse?, String?>> {
        return homeNetworkRepository.getHomeInfo().map { result ->
            Response.handleResponse(result)
        }
    }

    override fun getHomeInfoCityById(cityId: Int): Flow<Pair<HomeResponse?, String?>> {
        return homeNetworkRepository.getHomeInfoCityById(cityId).map { result ->
            Response.handleResponse(result)
        }
    }

    override fun getHomeInfoCityByLocation(lat: String, lng: String): Flow<Pair<HomeResponse?, String?>> {
        return homeNetworkRepository.getHomeInfoCityByLocation(
            lat = lat,
            lng = lng
        ).map { result ->
            Response.handleResponse(result)
        }
    }

    override suspend fun getLocation(): Pair<Location?, String?> {
        return Response.handleResponse(locationRepository.getLocation())
    }

    override suspend fun setLastCoordinate(location: LocationSp): Int {
        return locationRepositorySp.setLastCoordinate(location)
    }

    override suspend fun getLastCoordinate(): LocationSp? {
        return locationRepositorySp.getLastCoordinate()
    }

    override suspend fun deleteLastCoordinate(): Int {
        return locationRepositorySp.deleteLastCoordinate()
    }
}
