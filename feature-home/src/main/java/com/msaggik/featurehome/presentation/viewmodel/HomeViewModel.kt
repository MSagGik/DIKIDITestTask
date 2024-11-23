package com.msaggik.featurehome.presentation.viewmodel

import androidx.lifecycle.ViewModel
import com.msaggik.featurehome.domain.usecase.HomeInteractor

class HomeViewModel(
    private val homeInteractor: HomeInteractor
) : ViewModel() {
}
