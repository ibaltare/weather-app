package com.navi.weather.domain

data class WeatherForecast(val city: String,
                           val temperature: Double,
                           val weatherDescription: String,
                           val weatherIcon: String,
                           val weatherMain: String,
                           val humidity: Long,
                           val visibility: Long,
                           val windSpeed: Double
    )
