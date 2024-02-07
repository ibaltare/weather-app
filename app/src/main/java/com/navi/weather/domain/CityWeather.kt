package com.navi.weather.domain

data class CityWeather(val city: String,
                       val country: String,
                       val state: String?,
                       val weatherIcon: String,
                       val description: String,
                       val temp: Double,
                       val tempMax: Double,
                       val tempMin: Double)
