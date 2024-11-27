package com.msaggik.featurehome.data.repositoryimpl

import com.msaggik.datasp.api.LocationSharedPreferences
import com.msaggik.featurehome.data.mappers.Mappers
import com.msaggik.featurehome.domain.model.locationsp.LocationSp
import com.msaggik.featurehome.domain.repository.LocationRepositorySp

class LocationRepositorySpImpl(
    private val locationSharedPreferences: LocationSharedPreferences
) : LocationRepositorySp {

    override suspend fun setLastCoordinate(location: LocationSp): Int {
        return locationSharedPreferences.setLastCoordinate(Mappers.map(location))
    }

    override suspend fun getLastCoordinate(): LocationSp? {
        return Mappers.map(locationSharedPreferences.getLastCoordinate())
    }

    override suspend fun deleteLastCoordinate(): Int {
        return locationSharedPreferences.deleteLastCoordinate()
    }
}
