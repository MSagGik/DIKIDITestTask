package com.msaggik.datalocation.api.impl

import android.annotation.SuppressLint
import android.content.Context
import android.location.Location
import com.google.android.gms.location.CurrentLocationRequest
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.Granularity
import com.google.android.gms.location.LocationServices
import com.google.android.gms.location.Priority
import com.google.android.gms.tasks.Tasks
import com.msaggik.commonutils.all.Response
import com.msaggik.datalocation.api.LocationApi

private const val LONG_REQUEST = 10_000L
private const val MAX_AGE_DATA_LOCATION = 60_000L

internal class LocationApiImpl(
    private val context: Context
) : LocationApi {

    private val fusedLocationClient: FusedLocationProviderClient =
        LocationServices.getFusedLocationProviderClient(context)

    @SuppressLint("MissingPermission")
    override fun getLocation(): Response<Location> {
        return runCatching {
            val locationRequest = CurrentLocationRequest.Builder()
                .setPriority(Priority.PRIORITY_HIGH_ACCURACY)
                .setGranularity(Granularity.GRANULARITY_FINE)
                .setDurationMillis(LONG_REQUEST)
                .setMaxUpdateAgeMillis(MAX_AGE_DATA_LOCATION)
                .build()
            val task = fusedLocationClient.getCurrentLocation(locationRequest, null)
            val location = Tasks.await(task)
            if (location != null) {
                Response.Success(location)
            } else {
                Response.Error(context.getString(com.msaggik.commonui.R.string.no_data))
            }
        }.fold(
            onSuccess = { response ->
                response
            },
            onFailure = { e ->
                Response.Error("${context.getString(com.msaggik.commonui.R.string.no_data)}, exception ${e.message}")
            }
        )
    }
}
