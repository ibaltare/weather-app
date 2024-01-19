package com.navi.weather.model

import com.navi.weather.utils.Constants
import retrofit2.http.GET
import retrofit2.http.Query

interface RemoteService {
    @GET(Constants.WEATHER_DATA_PATH)
    suspend fun getWeatherData(
        @Query(Constants.LATITUDE_PARAM) lat: Double,
        @Query(Constants.LONGITUDE_PARAM) lon: Double,
        @Query(Constants.APP_ID_PARAM) appid: String,
        @Query(Constants.UNITS_PARAM) units: String,
        @Query(Constants.LANGUAGE_PARAM) lang: String,
    ): WeatherResult
}