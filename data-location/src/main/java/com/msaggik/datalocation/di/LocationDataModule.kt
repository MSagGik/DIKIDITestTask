package com.msaggik.datalocation.di

import com.msaggik.datalocation.api.LocationApi
import com.msaggik.datalocation.api.impl.LocationApiImpl
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module

val locationDataModule = module {
    // data
    single<LocationApi> {
        LocationApiImpl(androidContext())
    }
}
