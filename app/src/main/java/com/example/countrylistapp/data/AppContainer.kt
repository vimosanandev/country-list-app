package com.example.countrylistapp.data

import com.example.countrylistapp.db.AppDatabase
import com.example.countrylistapp.network.CountriesApiService
import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import kotlinx.serialization.json.Json
import retrofit2.Retrofit
import okhttp3.MediaType.Companion.toMediaType

interface AppContainer {
    val countriesRepository: CountriesRepository
}

class CountryListAppContainer(val db: AppDatabase) : AppContainer {
    private val baseUrl = "https://gist.githubusercontent.com/"

    private val json = Json {
        ignoreUnknownKeys = true
    }

    private val retrofit: Retrofit = Retrofit.Builder()
        .addConverterFactory(json.asConverterFactory("application/json".toMediaType()))
        .baseUrl(baseUrl)
        .build()

    private val retrofitService: CountriesApiService by lazy {
        retrofit.create(CountriesApiService::class.java)
    }

    override val countriesRepository: CountriesRepository by lazy {
        NetworkCountriesRepository(retrofitService, db)
    }
}