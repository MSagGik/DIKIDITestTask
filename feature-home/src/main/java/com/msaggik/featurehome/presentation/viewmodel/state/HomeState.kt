package com.msaggik.featurehome.presentation.viewmodel.state

import com.msaggik.featurehome.domain.model.HomeResponse

sealed interface HomeState {

    data object Loading : HomeState

    data class Content(
        val homeResponse: HomeResponse
    ) : HomeState

    data class Error(
        val errorMessage: String
    ) : HomeState
}
