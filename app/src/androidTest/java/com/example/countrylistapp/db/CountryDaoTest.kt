package com.example.countrylistapp.db

import android.content.Context
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.example.countrylistapp.db.dao.CountryDao
import com.example.countrylistapp.db.dao.CurrencyDao
import com.example.countrylistapp.db.dao.LanguageDao
import com.example.countrylistapp.db.entities.CountryEntity
import com.example.countrylistapp.db.entities.CurrencyEntity
import com.example.countrylistapp.db.entities.LanguageEntity
import com.example.countrylistapp.getOrAwaitValue
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.Assert.*
import java.io.IOException


@RunWith(AndroidJUnit4::class)
class CountryDaoTest {
    private lateinit var countryDao: CountryDao
    private lateinit var currencyDao: CurrencyDao
    private lateinit var languageDao: LanguageDao
    private lateinit var db: AppDatabase

    @Before
    fun createDb() {
        val context = ApplicationProvider.getApplicationContext<Context>()
        db = Room.inMemoryDatabaseBuilder(
            context, AppDatabase::class.java
        ).build()

        countryDao = db.countryDao()
        currencyDao = db.currencyDao()
        languageDao = db.languageDao()
    }

    @After
    @Throws(IOException::class)
    fun closeDb() {
        db.close()
    }

    @Test
    @Throws(Exception::class)
    fun writeAndReadInList() {
        val currency = CurrencyEntity("AFN", "Afghan afghani", "؋")
        val language = LanguageEntity("ps", "Pashto")
        val country = CountryEntity(
            "AF",
            "Afghanistan",
            "Kabul",
            "AS",
            "AFN",
            "ps",
            "https://restcountries.eu/data/afg.svg"
        )

        currencyDao.insertAll(listOf(currency))
        languageDao.insertAll(listOf(language))
        countryDao.insertAll(listOf(country))

        val countries = countryDao.getAllCountries().getOrAwaitValue()
        val currencies = currencyDao.getAllCurrencies().getOrAwaitValue()
        val languages = languageDao.getAllLanguages().getOrAwaitValue()

        assertEquals(1, countries.size)
        assertEquals(1, currencies.size)
        assertEquals(1, languages.size)
    }

    @Test
    @Throws(Exception::class)
    fun writeThenDeleteCurrencyAndReadInList() {
        val currency = CurrencyEntity("AFN", "Afghan afghani", "؋")
        val language = LanguageEntity("ps", "Pashto")
        val country = CountryEntity(
            "AF",
            "Afghanistan",
            "Kabul",
            "AS",
            "AFN",
            "ps",
            "https://restcountries.eu/data/afg.svg"
        )

        currencyDao.insertAll(listOf(currency))
        languageDao.insertAll(listOf(language))
        countryDao.insertAll(listOf(country))

        currencyDao.deleteByCode("AFN")

        val countries = countryDao.getAllCountries().getOrAwaitValue()
        val languages = languageDao.getAllLanguages().getOrAwaitValue()

        assertEquals(0, countries.size)
        assertEquals(1, languages.size)
    }

    @Test
    @Throws(Exception::class)
    fun writeThenDeleteLanguageAndReadInList() {
        val currency = CurrencyEntity("AFN", "Afghan afghani", "؋")
        val language = LanguageEntity("ps", "Pashto")
        val country = CountryEntity(
            "AF",
            "Afghanistan",
            "Kabul",
            "AS",
            "AFN",
            "ps",
            "https://restcountries.eu/data/afg.svg"
        )

        currencyDao.insertAll(listOf(currency))
        languageDao.insertAll(listOf(language))
        countryDao.insertAll(listOf(country))

        languageDao.deleteByCode("ps")

        val countries = countryDao.getAllCountries().getOrAwaitValue()
        val currencies = currencyDao.getAllCurrencies().getOrAwaitValue()

        assertEquals(0, countries.size)
        assertEquals(1, currencies.size)
    }
}