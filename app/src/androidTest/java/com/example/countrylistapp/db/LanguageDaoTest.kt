package com.example.countrylistapp.db

import android.content.Context
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.example.countrylistapp.db.dao.LanguageDao
import com.example.countrylistapp.db.entities.LanguageEntity
import com.example.countrylistapp.getOrAwaitValue
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.Assert.*
import java.io.IOException


@RunWith(AndroidJUnit4::class)
class LanguageDaoTest {
    private lateinit var languageDao: LanguageDao
    private lateinit var db: AppDatabase

    @Before
    fun createDb() {
        val context = ApplicationProvider.getApplicationContext<Context>()
        db = Room.inMemoryDatabaseBuilder(
            context, AppDatabase::class.java
        ).build()

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
        val language = LanguageEntity("ps", "Pashto")

        languageDao.insertAll(listOf(language))

        val languages = languageDao.getAllLanguages().getOrAwaitValue()

        assertEquals(1, languages.size)
    }

    @Test
    @Throws(Exception::class)
    fun writeThenDeleteLanguageAndReadInList() {
        val language = LanguageEntity("ps", "Pashto")

        languageDao.insertAll(listOf(language))

        languageDao.deleteByCode("ps")

        val languages = languageDao.getAllLanguages().getOrAwaitValue()

        assertEquals(0, languages.size)
    }
}