package com.example.countrylistapp

import android.app.Application
import com.example.countrylistapp.data.AppContainer
import com.example.countrylistapp.data.CountryListAppContainer

class CountryListApplication : Application() {
    lateinit var container: AppContainer

    override fun onCreate() {
        super.onCreate()
        container = CountryListAppContainer()
    }
}