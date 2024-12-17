package com.example.countrylistapp.model

import com.example.countrylistapp.db.entities.CountryEntity
import kotlinx.serialization.Serializable

@Serializable
data class Country(
    val capital: String?,
    val code: String?,
    val currency: Currency?,
    val flag: String?,
    val language: Language?,
    val name: String?,
    val region: String?
) {
    fun toCountryEntity(): CountryEntity? {
        return code?.let {
            CountryEntity(
                code = code,
                name = name,
                capital = capital,
                region = region,
                currencyCode = currency?.code,
                languageCode = language?.code,
                flag = flag
            )
        }
    }
}