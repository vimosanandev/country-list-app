package com.example.countrylistapp.db.entities

import junit.framework.TestCase.assertEquals
import org.junit.Test

class CountryWithDetailsEntityTest {

    @Test
    fun toCountry() {
        val countryWithDetailsEntity = CountryWithDetailsEntity(
            code = "AF",
            name = "Afghanistan",
            capital = "Kabul",
            region = "AS",
            currencyCode = "AFN",
            currencyName = "Afghan afghani",
            currencySymbol = "؋",
            languageCode = "ps",
            languageName = "Pashto",
            flag = "https://restcountries.eu/data/afg.svg"
        )

        val country = countryWithDetailsEntity.toCountry()

        assertEquals("AF", country.code)
        assertEquals("Afghanistan", country.name)
        assertEquals("Kabul", country.capital)
        assertEquals("AS", country.region)
        assertEquals("AFN", country.currency?.code)
        assertEquals("Afghan afghani", country.currency?.name)
        assertEquals("؋", country.currency?.symbol)
        assertEquals("ps", country.language?.code)
        assertEquals("Pashto", country.language?.name)
        assertEquals("https://restcountries.eu/data/afg.svg", country.flag)
    }
}