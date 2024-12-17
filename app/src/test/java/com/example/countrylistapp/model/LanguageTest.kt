package com.example.countrylistapp.model

import junit.framework.TestCase.assertEquals
import org.junit.Test

class LanguageTest {
    @Test
    fun toLanguageEntity() {
        val currency = Language("ps", "Pashto")

        val entity = currency.toLanguageEntity()

        assertEquals("ps", entity?.code)
        assertEquals("Pashto", entity?.name)
    }
}