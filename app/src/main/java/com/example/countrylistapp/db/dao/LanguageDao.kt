package com.example.countrylistapp.db.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.countrylistapp.db.entities.LanguageEntity

@Dao
interface LanguageDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAll(languages: List<LanguageEntity>)

    @Query("SELECT * FROM languages")
    fun getAllLanguages(): LiveData<List<LanguageEntity>>
}