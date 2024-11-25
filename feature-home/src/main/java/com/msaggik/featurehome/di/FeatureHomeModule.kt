package com.msaggik.featurehome.di

import com.msaggik.featurehome.data.repositoryimpl.HomeNetworkRepositoryImpl
import com.msaggik.featurehome.domain.repository.HomeNetworkRepository
import com.msaggik.featurehome.domain.usecase.HomeInteractor
import com.msaggik.featurehome.domain.usecase.impl.HomeInteractorImpl
import com.msaggik.featurehome.presentation.viewmodel.HomeViewModel
import org.koin.android.ext.koin.androidContext
import org.koin.core.module.dsl.viewModel
import org.koin.dsl.module

val featureHomeModule = module {
    // view-model
    viewModel {
        HomeViewModel(
            homeInteractor = get()
        )
    }
    // domain
    single<HomeInteractor> {
        HomeInteractorImpl(
            homeNetworkRepository = get()
        )
    }
    // data
    single<HomeNetworkRepository> {
        HomeNetworkRepositoryImpl(
            androidContext(),
            networkClient = get()
        )
    }
}
