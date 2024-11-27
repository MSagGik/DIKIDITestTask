package com.msaggik.featurehome.domain.repository

import com.msaggik.featurehome.domain.model.locationsp.LocationSp

interface LocationRepositorySp {
    suspend fun setLastCoordinate(location: LocationSp) : Int
    suspend fun getLastCoordinate() : LocationSp?
    suspend fun deleteLastCoordinate() : Int
}
