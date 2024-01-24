package com.navi.weather.model

import android.location.Location
import com.navi.weather.BuildConfig
import com.navi.weather.domain.WeatherForecast
import com.navi.weather.mappers.RemoteWeatherToPresentationMapper

class WeatherRepository() {

    private val remoteWeatherToPresentationMapper = RemoteWeatherToPresentationMapper()
    suspend fun getWeatherForecast(location: Location) =
        remoteWeatherToPresentationMapper.map(
            RemoteConnection.service.getWeatherForecast(
                location.latitude,
                location.longitude,
                BuildConfig.weather_api_key,
                "metric",
                "es"
            )
        )
}