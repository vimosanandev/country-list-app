package com.example.countrylistapp

import android.app.Application
import androidx.room.Room
import com.example.countrylistapp.data.AppContainer
import com.example.countrylistapp.data.CountryListAppContainer
import com.example.countrylistapp.db.AppDatabase

class CountryListApplication : Application() {
    lateinit var appContainer: AppContainer

    override fun onCreate() {
        super.onCreate()

        val db = Room.databaseBuilder(
            applicationContext,
            AppDatabase::class.java,
            "country_db"
        ).fallbackToDestructiveMigration()
            .build()

        appContainer = CountryListAppContainer(db)
    }
}