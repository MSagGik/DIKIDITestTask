package com.msaggik.datasp.api.impl

import android.content.SharedPreferences
import com.google.gson.Gson
import com.msaggik.datasp.api.LocationSharedPreferences
import com.msaggik.datasp.api.dto.LocationDto

private const val LOCATION_KEY_SP = "location_key_sp"

internal class LocationSharedPreferencesImpl(
    private val coordinateSp: SharedPreferences,
    private val gson: Gson,
) : LocationSharedPreferences {

    override fun setLastCoordinate(location: LocationDto): Int {
        return runCatching {
            val json = gson.toJson(location)
            coordinateSp.edit()
                .putString(LOCATION_KEY_SP, json)
                .apply()
        }.fold(
            onSuccess = { 1 },
            onFailure = { -1 }
        )
    }

    override fun getLastCoordinate(): LocationDto? {
        val json = coordinateSp.getString(LOCATION_KEY_SP, null)
        return if (json != null) {
            gson.fromJson(json, LocationDto::class.java)
        } else {
            null
        }
    }

    override fun deleteLastCoordinate(): Int {
        val editor = coordinateSp.edit()
        val exists = coordinateSp.contains(LOCATION_KEY_SP)
        return if (exists) {
            editor.remove(LOCATION_KEY_SP).apply()
            1
        } else {
            -1
        }
    }
}
