package com.msaggik.datasp.api.dto

data class LocationDto(
    val latitude: Double,
    val longitude: Double,
    val street: String?,
    val house: String?
)
