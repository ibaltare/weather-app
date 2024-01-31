package com.navi.weather.domain

data class CityWeather(val city: String,
                       val temperature: Double,
                       val weatherDescription: String,
                       val weatherIcon: String,
                       val weatherMain: String,
                       val humidity: Long,
                       val visibility: Long,
                       val windSpeed: Double)
