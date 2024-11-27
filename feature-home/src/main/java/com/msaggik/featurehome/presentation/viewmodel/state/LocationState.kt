package com.msaggik.featurehome.presentation.viewmodel.state

import com.msaggik.featurehome.domain.model.location.Location

sealed interface LocationState {
    data object Loading : LocationState

    data class Content(
        val location: Location
    ) : LocationState

    data class Error(
        val message: String
    ) : LocationState
}
