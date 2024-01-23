package com.navi.weather.model

import android.location.Location
import com.navi.weather.BuildConfig

class WeatherRepository() {

    suspend fun getWeatherData(location: Location) =
            RemoteConnection.service.getWeatherData(
                location.latitude,
                location.longitude,
                BuildConfig.weather_api_key,
                "metric",
                "es"
            )

}