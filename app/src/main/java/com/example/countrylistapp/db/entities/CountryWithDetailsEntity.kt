package com.example.countrylistapp.db.entities

import com.example.countrylistapp.model.Country
import com.example.countrylistapp.model.Currency
import com.example.countrylistapp.model.Language

data class CountryWithDetailsEntity(
    val code: String,
    val name: String?,
    val capital: String?,
    val region: String?,
    val currencyCode: String?,
    val currencyName: String?,
    val currencySymbol: String?,
    val languageCode: String?,
    val languageName: String?,
    val flag: String?,
) {
    fun toCountry() = Country(
        code = code,
        name = name,
        capital = capital,
        region = region,
        currency = Currency(
            code = currencyCode,
            name = currencyName,
            symbol = currencySymbol
        ),
        language = Language(
            code = languageCode,
            name = languageName
        ),
        flag = flag
    )
}