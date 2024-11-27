package com.msaggik.datalocation.api

import android.location.Location
import com.msaggik.commonutils.all.Response

interface LocationApi {
    fun getLocation(): Response<Location>
}
