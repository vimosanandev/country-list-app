package com.example.countrylistapp.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.countrylistapp.db.dao.CountryDao
import com.example.countrylistapp.db.dao.CurrencyDao
import com.example.countrylistapp.db.dao.LanguageDao
import com.example.countrylistapp.db.entities.CountryEntity
import com.example.countrylistapp.db.entities.CurrencyEntity
import com.example.countrylistapp.db.entities.LanguageEntity

@Database(
    entities = [CountryEntity::class, CurrencyEntity::class, LanguageEntity::class],
    version = 1,
)
abstract class AppDatabase : RoomDatabase() {
    abstract fun countryDao(): CountryDao
    abstract fun currencyDao(): CurrencyDao
    abstract fun languageDao(): LanguageDao

    companion object {
        @Volatile
        private var INSTANCE: AppDatabase? = null

        fun getDatabase(context: android.content.Context): AppDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = androidx.room.Room.databaseBuilder(
                    context.applicationContext,
                    AppDatabase::class.java,
                    "app_database"
                ).build()
                INSTANCE = instance
                instance
            }
        }
    }
}