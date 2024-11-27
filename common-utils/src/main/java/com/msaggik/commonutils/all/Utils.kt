package com.msaggik.commonutils.all

import android.content.Context
import android.util.Log
import com.msaggik.commonui.R
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import java.util.Locale
import kotlin.math.atan2
import kotlin.math.cos
import kotlin.math.pow
import kotlin.math.round
import kotlin.math.sin
import kotlin.math.sqrt

private const val EARTH_RADIUS = 6371.0

object Utils {
    fun outputStackTrace(tag: String, e: Throwable) {
        e.stackTrace.forEach { element ->
            Log.e(
                tag,
                "Class: ${element.className}, Method: ${element.methodName}, Line: ${element.lineNumber}"
            )
        }
    }
    fun formatDate(
        context: Context,
        locale: Locale,
        inputDate: String
    ): String {
        val inputFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")
        val outputFormatter = DateTimeFormatter.ofPattern("dd MMM", locale)

        return runCatching {
            val dateTime = LocalDateTime.parse(inputDate, inputFormatter)
            outputFormatter.format(dateTime)
        }.getOrElse {
            context.getString(R.string.incorrect_date)
        }
    }

    /**
     * Calculates the distance between two geographical coordinates (latitude and longitude) using the
     * Haversine formula. This algorithm determines the distance along the surface of a sphere
     * (Earth) and accounts for its curvature.
     *
     * @param latitude1 Latitude of the first point in degrees.
     * @param longitude1 Longitude of the first point in degrees.
     * @param latitude2 Latitude of the second point in degrees.
     * @param longitude2 Longitude of the second point in degrees.
     * @return The distance between the two points in kilometers.
     */
    fun haversine(
        latitude1: Double,
        longitude1: Double,
        latitude2: Double,
        longitude2: Double
    ): Double {
        val earthRadius = EARTH_RADIUS
        val deltaLatitude = Math.toRadians(latitude2 - latitude1)
        val deltaLongitude = Math.toRadians(longitude2 - longitude1)

        val a = sin(deltaLatitude / 2).pow(2.0) +
            cos(Math.toRadians(latitude1)) * cos(Math.toRadians(latitude2)) *
            sin(deltaLongitude / 2).pow(2.0)

        val c = 2 * atan2(sqrt(a), sqrt(1 - a))

        val factor = 10.0.pow(2)

        return round(earthRadius * c * factor) / factor
    }
}
