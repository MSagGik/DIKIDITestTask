package com.msaggik.datanetwork.di

import com.msaggik.datanetwork.api.NetworkClient
import com.msaggik.datanetwork.api.network.ApiService
import com.msaggik.datanetwork.api.network.RetrofitNetworkClient
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

private const val BASE_URL = "https://api-beauty.test.dikidi.ru/"

val networkDataModule = module {

    single<ApiService> {
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .client(
                OkHttpClient.Builder()
                    .addInterceptor(HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY))
                    .build()
            )
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(ApiService::class.java)
    }

    single<NetworkClient> {
        RetrofitNetworkClient(
            androidContext(),
            get()
        )
    }
}
