package com.example.countrylistapp.model

import junit.framework.TestCase.assertEquals
import org.junit.Test


class CurrencyTest {

    @Test
    fun toCurrencyEntity() {
        val currency = Currency("AFN", "Afghan afghani", "؋")

        val entity = currency.toCurrencyEntity()

        assertEquals("AFN", entity?.code)
        assertEquals("Afghan afghani", entity?.name)
        assertEquals("؋", entity?.symbol)
    }
}