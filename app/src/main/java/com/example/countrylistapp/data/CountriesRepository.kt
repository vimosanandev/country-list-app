package com.example.countrylistapp.data

import com.example.countrylistapp.model.Country
import com.example.countrylistapp.network.CountriesApiService

interface CountriesRepository {
    suspend fun getCountries(): List<Country>
}

class NetworkCountriesRepository(
    private val countriesApiService: CountriesApiService
) : CountriesRepository {
    override suspend fun getCountries(): List<Country> = countriesApiService.getCountries()
}