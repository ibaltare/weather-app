package com.navi.weather.model

import android.location.Location

class WeatherRepository() {

    suspend fun getWeatherData(location: Location) =
            RemoteConnection.service.getWeatherData(
                location.latitude,
                location.longitude,
                "",
                "metric",
                "es"
            )

}