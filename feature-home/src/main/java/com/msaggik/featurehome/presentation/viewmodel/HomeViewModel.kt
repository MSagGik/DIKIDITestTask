package com.msaggik.featurehome.presentation.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.msaggik.featurehome.domain.usecase.HomeInteractor
import com.msaggik.featurehome.presentation.viewmodel.state.HomeState
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

private const val DEFAULT_ID_CITY = 468_902

class HomeViewModel(
    private val homeInteractor: HomeInteractor
) : ViewModel() {

    private var _homeStateLiveData = MutableLiveData<HomeState>()
    val homeStateLiveData: LiveData<HomeState> = _homeStateLiveData

    private fun getHomeInfo() {
        viewModelScope.launch(Dispatchers.IO) {
            _homeStateLiveData.postValue(HomeState.Loading)
            homeInteractor.getHomeInfo().collect { response ->
                if (response.first != null) {
                    response.first?.let { _homeStateLiveData.postValue(HomeState.Content(it)) }
                } else {
                    response.second?.let { _homeStateLiveData.postValue(HomeState.Error(it)) }
                }
            }
        }
    }

    private fun getHomeInfoCityById() {
        viewModelScope.launch(Dispatchers.IO) {
            _homeStateLiveData.postValue(HomeState.Loading)
            homeInteractor.getHomeInfoCityById(DEFAULT_ID_CITY).collect { response ->
                if (response.first != null) {
                    response.first?.let { _homeStateLiveData.postValue(HomeState.Content(it)) }
                } else {
                    response.second?.let { _homeStateLiveData.postValue(HomeState.Error(it)) }
                }
            }
        }
    }

    private fun getHomeInfoCityByLocation(
        lat: String,
        lng: String
    ) {
        viewModelScope.launch(Dispatchers.IO) {
            _homeStateLiveData.postValue(HomeState.Loading)
            homeInteractor.getHomeInfoCityByLocation(
                lat = lat,
                lng = lng
            ).collect { response ->
                if (response.first != null) {
                    response.first?.let { _homeStateLiveData.postValue(HomeState.Content(it)) }
                } else {
                    response.second?.let { _homeStateLiveData.postValue(HomeState.Error(it)) }
                }
            }
        }
    }

    init {
        getHomeInfoCityById()
    }
}
