package com.msaggik.featurehome.domain.repository

import com.msaggik.commonutils.all.Response
import com.msaggik.featurehome.domain.model.location.Location

interface LocationRepository {
    suspend fun getLocation() : Response<Location>
}
