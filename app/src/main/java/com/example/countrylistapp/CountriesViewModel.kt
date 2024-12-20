package com.example.countrylistapp

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.example.countrylistapp.data.CountriesRepository
import com.example.countrylistapp.model.Country
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class CountriesViewModel(private val countriesRepository: CountriesRepository) : ViewModel() {
    val countries: LiveData<List<Country>> = countriesRepository.getCountries()

    fun fetchCountries() {
        viewModelScope.launch {
            withContext(Dispatchers.IO) {
                countriesRepository.fetchCountries()
            }
        }
    }

    companion object {
        fun provideFactory(
            countriesRepository: CountriesRepository
        ): ViewModelProvider.Factory = object : ViewModelProvider.Factory {
            override fun <T : ViewModel> create(modelClass: Class<T>): T {
                if (modelClass.isAssignableFrom(CountriesViewModel::class.java)) {
                    @Suppress("UNCHECKED_CAST")
                    return CountriesViewModel(countriesRepository) as T
                }
                throw IllegalArgumentException("Unknown ViewModel class")
            }
        }
    }
}