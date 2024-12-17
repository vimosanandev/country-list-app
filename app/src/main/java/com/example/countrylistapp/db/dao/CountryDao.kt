package com.example.countrylistapp.db.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.countrylistapp.db.entities.CountryEntity
import com.example.countrylistapp.db.entities.CountryWithDetailsEntity

@Dao
interface CountryDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAll(countries: List<CountryEntity>)

    @Query("SELECT * FROM countries")
    fun getAllCountries(): LiveData<List<CountryEntity>>

    @Query("""
    SELECT 
        countries.*,
        currencies.name AS currencyName,
        currencies.symbol AS currencySymbol,
        languages.name AS languageName
    FROM countries 
    INNER JOIN currencies ON countries.currencyCode = currencies.code 
    INNER JOIN languages ON countries.languageCode = languages.code
""")
    fun getAllCountriesWithDetails(): LiveData<List<CountryWithDetailsEntity>>
}