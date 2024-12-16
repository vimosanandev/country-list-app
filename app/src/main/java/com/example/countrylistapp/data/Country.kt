package com.example.countrylistapp.data

/*
* {
    "capital": "Kabul",
    "code": "AF",
    "currency": {
      "code": "AFN",
      "name": "Afghan afghani",
      "symbol": "؋"
    },
    "flag": "https://restcountries.eu/data/afg.svg",
    "language": {
      "code": "ps",
      "name": "Pashto"
    },
    "name": "Afghanistan",
    "region": "AS"
  },
* */
data class Country(
    val capital: String,
    val code: String,
    val currency: Currency,
    val flag: String,
    val language: Language,
    val name: String,
    val region: String
)