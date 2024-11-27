package com.msaggik.dikiditesttask.root

import android.app.Application
import com.msaggik.datalocation.di.locationDataModule
import com.msaggik.datanetwork.di.networkDataModule
import com.msaggik.datasp.di.settingsDataModule
import com.msaggik.featurehome.di.featureHomeModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class App : Application() {

    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidContext(this@App)
            modules(
                networkDataModule,
                locationDataModule,
                settingsDataModule,
                featureHomeModule
            )
        }
    }
}
