package com.msaggik.datasp.api

import com.msaggik.datasp.api.dto.LocationDto

interface LocationSharedPreferences {
    fun setLastCoordinate(location: LocationDto) : Int
    fun getLastCoordinate() : LocationDto?
    fun deleteLastCoordinate() : Int
}
