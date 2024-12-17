package com.example.countrylistapp.model

import com.example.countrylistapp.db.entities.LanguageEntity
import kotlinx.serialization.Serializable

@Serializable
data class Language(
    val code: String?,
    val name: String?
) {
    fun toLanguageEntity(): LanguageEntity? {
        return code?.let {
            LanguageEntity(
                code = it,
                name = name
            )
        }
    }
}
