package com.example.countrylistapp.model

sealed class CountryItem {
    data class Header(val startsAt: String) : CountryItem()
    data class ItemCountry(val country: Country) : CountryItem()
}

