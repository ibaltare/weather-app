package com.navi.weather.model

import android.location.Location
import com.navi.weather.BuildConfig
import com.navi.weather.domain.CityWeather

class PlaceRepository {
    suspend fun getWeatherForecastFromPlace(element: GeocodingElement): CityWeather {
        val result = RemoteConnection.service.getWeatherForecast(
                element.lat,
                element.lon,
                BuildConfig.weather_api_key,
                "metric",
                "es"
            )
        return weatherResultToPlace(result,element)
    }

    private fun weatherResultToPlace(weatherResult: WeatherResult, element: GeocodingElement ): CityWeather {
        return CityWeather(element.name,
            element.country,
            element.state,
            weatherResult.weather[0].icon,
            weatherResult.main.temp,
            weatherResult.main.tempMax,
            weatherResult.main.tempMin)
    }

}