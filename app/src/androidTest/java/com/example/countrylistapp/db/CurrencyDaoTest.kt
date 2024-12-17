package com.example.countrylistapp.db

import android.content.Context
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.example.countrylistapp.db.dao.CurrencyDao
import com.example.countrylistapp.db.entities.CurrencyEntity
import com.example.countrylistapp.getOrAwaitValue
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.Assert.*
import java.io.IOException


@RunWith(AndroidJUnit4::class)
class CurrencyDaoTest {
    private lateinit var currencyDao: CurrencyDao
    private lateinit var db: AppDatabase

    @Before
    fun createDb() {
        val context = ApplicationProvider.getApplicationContext<Context>()
        db = Room.inMemoryDatabaseBuilder(
            context, AppDatabase::class.java
        ).build()

        currencyDao = db.currencyDao()
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

        currencyDao.insertAll(listOf(currency))

        val currencies = currencyDao.getAllCurrencies().getOrAwaitValue()

        assertEquals(1, currencies.size)
    }

    @Test
    @Throws(Exception::class)
    fun writeThenDeleteCurrencyAndReadInList() {
        val currency = CurrencyEntity("AFN", "Afghan afghani", "؋")

        currencyDao.insertAll(listOf(currency))

        currencyDao.deleteByCode("AFN")

        val currencies = currencyDao.getAllCurrencies().getOrAwaitValue()

        assertEquals(0, currencies.size)
    }
}