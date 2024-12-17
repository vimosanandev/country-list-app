package com.example.countrylistapp.model

import com.example.countrylistapp.db.entities.CurrencyEntity
import kotlinx.serialization.Serializable

@Serializable
data class Currency(
    val code: String?,
    val name: String?,
    val symbol: String?
) {
    fun toCurrencyEntity(): CurrencyEntity? {
        return code?.let {
            CurrencyEntity(
                code = it,
                name = name,
                symbol = symbol
            )
        }
    }
}
