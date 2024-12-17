package com.example.countrylistapp.model

import junit.framework.TestCase.assertEquals
import junit.framework.TestCase.assertNotNull
import org.junit.Test

class CountryTest {

    @Test
    fun toCountryEntity() {
        val country = Country(
            "Kabul",
            "AF",
            Currency("AFN", "Afghan afghani", "؋"),
            "https://restcountries.eu/data/afg.svg",
            Language("ps", "Pashto"),
            "Afghanistan",
            "AS"
        )

        val entity = country.toCountryEntity()

        assertNotNull(entity)
        assertEquals("AF", entity?.code)
        assertEquals("Afghanistan", entity?.name)
        assertEquals("Kabul", entity?.capital)
        assertEquals("AS", entity?.region)
        assertEquals("AFN", entity?.currencyCode)
        assertEquals("ps", entity?.languageCode)
        assertEquals("https://restcountries.eu/data/afg.svg", entity?.flag)
    }
}