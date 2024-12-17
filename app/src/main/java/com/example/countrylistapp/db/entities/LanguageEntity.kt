package com.example.countrylistapp.db.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "languages")
data class LanguageEntity(
    @PrimaryKey val code: String,
    val name: String?
)