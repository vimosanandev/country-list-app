package com.example.countrylistapp.data

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.switchMap
import com.example.countrylistapp.db.AppDatabase
import com.example.countrylistapp.model.Country
import com.example.countrylistapp.network.CountriesApiService
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

interface CountriesRepository {
    fun getCountries(): LiveData<List<Country>>
    suspend fun fetchCountries()
}

class NetworkCountriesRepository(
    private val countriesApiService: CountriesApiService,
    private val db: AppDatabase,
) : CountriesRepository {

    override fun getCountries(): LiveData<List<Country>> {
        return db.countryDao().getAllCountriesWithDetails().switchMap {
            MutableLiveData(it.map { countryEntity ->
                countryEntity.toCountry()
            })
        }
    }

    override suspend fun fetchCountries() {
        val countries = countriesApiService.getCountries()

        countries.let {
            val countryEntities = it.mapNotNull { country -> country.toCountryEntity() }

            val currencyEntities = it.mapNotNull { country -> country.currency?.toCurrencyEntity() }
                .distinctBy { it.code }

            val languageEntities = it.mapNotNull { country -> country.language?.toLanguageEntity() }
                .distinctBy { it.code }

            withContext(Dispatchers.IO) {
                db.runInTransaction {
                    db.currencyDao().insertAll(currencyEntities)
                    db.languageDao().insertAll(languageEntities)
                    db.countryDao().insertAll(countryEntities)
                }
            }
        }
    }
}