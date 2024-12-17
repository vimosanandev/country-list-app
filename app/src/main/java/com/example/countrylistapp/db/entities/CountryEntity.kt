package com.example.countrylistapp.db.entities

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey

@Entity(
    tableName = "countries",
    foreignKeys = [
        ForeignKey(
            entity = CurrencyEntity::class,
            parentColumns = ["code"],
            childColumns = ["currencyCode"],
            onDelete = ForeignKey.CASCADE
        ),
        ForeignKey(
            entity = LanguageEntity::class,
            parentColumns = ["code"],
            childColumns = ["languageCode"],
            onDelete = ForeignKey.CASCADE
        )
    ]
)
data class CountryEntity(
    @PrimaryKey val code: String,
    val name: String?,
    val capital: String?,
    val region: String?,
    val currencyCode: String?,
    val languageCode: String?,
    val flag: String?
)