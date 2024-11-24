package com.msaggik.dikiditesttask.root

import android.app.Application
import com.msaggik.datanetwork.di.networkDataModule
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
                featureHomeModule
            )
        }
    }
}
