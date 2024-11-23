package com.msaggik.featurehome.data.repositoryimpl

import android.content.Context
import com.msaggik.commonutils.all.Resource
import com.msaggik.commonutils.network.Response
import com.msaggik.commonutils.network.executeNetworkRequest
import com.msaggik.datanetwork.api.NetworkClient
import com.msaggik.datanetwork.api.dto.HomeResponseDto
import com.msaggik.datanetwork.api.dto.request.HomeRequestByIdCityDto
import com.msaggik.datanetwork.api.dto.request.HomeRequestByIdLocationDto
import com.msaggik.datanetwork.api.dto.request.HomeRequestDto
import com.msaggik.featurehome.data.mappers.Mappers
import com.msaggik.featurehome.domain.model.HomeResponse
import com.msaggik.featurehome.domain.repository.HomeNetworkRepository
import kotlinx.coroutines.flow.Flow

class HomeNetworkRepositoryImpl(
    private val context: Context,
    private val networkClient: NetworkClient,
) : HomeNetworkRepository {

    override fun getHomeInfo(): Flow<Resource<HomeResponse>> {
        return context.executeNetworkRequest(
            request = {
                networkClient.doRequest(HomeRequestDto)
            },
            successHandler = { response: Response ->
                Resource.Success(
                    Mappers.map(response as HomeResponseDto)
                )
            }
        )
    }

    override fun getHomeInfoCityById(cityId: Int): Flow<Resource<HomeResponse>> {
        return context.executeNetworkRequest(
            request = {
                networkClient.doRequest(
                    HomeRequestByIdCityDto(cityId)
                )
            },
            successHandler = { response: Response ->
                Resource.Success(
                    Mappers.map(response as HomeResponseDto)
                )
            }
        )
    }

    override fun getHomeInfoCityByLocation(lat: String, lng: String): Flow<Resource<HomeResponse>> {
        return context.executeNetworkRequest(
            request = {
                networkClient.doRequest(
                    HomeRequestByIdLocationDto(
                        lat = lat,
                        lng = lng
                    )
                )
            },
            successHandler = { response: Response ->
                Resource.Success(
                    Mappers.map(response as HomeResponseDto)
                )
            }
        )
    }

}
