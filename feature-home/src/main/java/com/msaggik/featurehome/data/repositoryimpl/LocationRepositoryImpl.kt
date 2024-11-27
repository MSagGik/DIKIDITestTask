package com.msaggik.featurehome.data.repositoryimpl

import com.msaggik.commonutils.all.Response
import com.msaggik.commonutils.all.map
import com.msaggik.datalocation.api.LocationApi
import com.msaggik.featurehome.data.mappers.Mappers
import com.msaggik.featurehome.domain.model.location.Location
import com.msaggik.featurehome.domain.repository.LocationRepository

class LocationRepositoryImpl(
    private val locationApi: LocationApi
) : LocationRepository {

    override suspend fun getLocation(): Response<Location> {
        return locationApi.getLocation().map { locationApi -> Mappers.map(locationApi) }
    }
}
