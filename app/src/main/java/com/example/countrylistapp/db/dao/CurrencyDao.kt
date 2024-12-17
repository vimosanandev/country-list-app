package com.example.countrylistapp.db.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.countrylistapp.db.entities.CurrencyEntity

@Dao
interface CurrencyDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAll(currencies: List<CurrencyEntity>)

    @Query("SELECT * FROM currencies")
    fun getAllCurrencies(): LiveData<List<CurrencyEntity>>

    @Query("DELETE FROM currencies WHERE code = :code")
    fun deleteByCode(code: String)
}