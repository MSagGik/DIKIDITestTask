package com.msaggik.featurehome.domain.usecase.impl

import com.msaggik.commonutils.all.Resource
import com.msaggik.featurehome.domain.model.HomeResponse
import com.msaggik.featurehome.domain.repository.HomeNetworkRepository
import com.msaggik.featurehome.domain.usecase.HomeInteractor
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class HomeInteractorImpl(
    private val homeNetworkRepository: HomeNetworkRepository,
) : HomeInteractor {

    override fun getHomeInfo(): Flow<Pair<HomeResponse?, String?>> {
        return homeNetworkRepository.getHomeInfo().map { result ->
            Resource.handleResource(result)
        }
    }

    override fun getHomeInfoCityById(cityId: Int): Flow<Pair<HomeResponse?, String?>> {
        return homeNetworkRepository.getHomeInfoCityById(cityId).map { result ->
            Resource.handleResource(result)
        }
    }

    override fun getHomeInfoCityByLocation(lat: String, lng: String): Flow<Pair<HomeResponse?, String?>> {
        return homeNetworkRepository.getHomeInfoCityByLocation(
            lat = lat,
            lng = lng
        ).map { result ->
            Resource.handleResource(result)
        }
    }
}
