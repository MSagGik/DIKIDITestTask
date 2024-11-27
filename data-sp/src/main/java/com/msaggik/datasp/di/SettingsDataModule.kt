package com.msaggik.datasp.di

import android.content.Context
import android.content.SharedPreferences
import androidx.security.crypto.EncryptedSharedPreferences
import androidx.security.crypto.MasterKey
import com.google.gson.Gson
import com.msaggik.datasp.api.LocationSharedPreferences
import com.msaggik.datasp.api.impl.LocationSharedPreferencesImpl
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module

private const val LOCATION_PREFERENCES_ENCRYPTED_KEY = "location_preferences_encrypted_key"

val settingsDataModule = module {

    // location
    single<LocationSharedPreferences> {
        LocationSharedPreferencesImpl(
            coordinateSp = androidContext().getEncryptedSharedPreferences(LOCATION_PREFERENCES_ENCRYPTED_KEY),
            gson = get()
        )
    }

    factory {
        Gson()
    }
}

private fun Context.getEncryptedSharedPreferences(
    nameDataFile: String
) : SharedPreferences {
    return EncryptedSharedPreferences.create(
        this,
        nameDataFile,
        MasterKey.Builder(this)
            .setKeyScheme(MasterKey.KeyScheme.AES256_GCM)
            .build(),
        EncryptedSharedPreferences.PrefKeyEncryptionScheme.AES256_SIV,
        EncryptedSharedPreferences.PrefValueEncryptionScheme.AES256_GCM
    )
}
