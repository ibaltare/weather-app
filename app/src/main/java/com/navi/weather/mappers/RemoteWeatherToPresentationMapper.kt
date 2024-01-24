package com.navi.weather.mappers

import com.navi.weather.domain.WeatherForecast
import com.navi.weather.model.WeatherResult

class RemoteWeatherToPresentationMapper {

    fun map(weatherResult: WeatherResult): WeatherForecast {
        return WeatherForecast(
            weatherResult.name,
            weatherResult.main.temp,
            weatherResult.weather[0].description,
            weatherResult.weather[0].icon,
            weatherResult.weather[0].main,
            weatherResult.main.humidity,
            weatherResult.visibility,
            weatherResult.wind.speed
        )
    }
}