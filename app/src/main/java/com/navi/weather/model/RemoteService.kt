package com.navi.weather.model

import com.navi.weather.utils.Constants
import retrofit2.http.GET
import retrofit2.http.Query

interface RemoteService {
    @GET(Constants.WEATHER_DATA_PATH)
    suspend fun getWeatherForecast(
        @Query(Constants.LATITUDE_PARAM) lat: Double,
        @Query(Constants.LONGITUDE_PARAM) lon: Double,
        @Query(Constants.APP_ID_PARAM) appid: String,
        @Query(Constants.UNITS_PARAM) units: String,
        @Query(Constants.LANGUAGE_PARAM) lang: String,
    ): WeatherResult

    @GET(Constants.GEOCODING_PATH)
    suspend fun getCoordinates (
        @Query(Constants.CITY_PARAM) city: String,
        @Query(Constants.LIMIT_PARAM) limit: Int,
        @Query(Constants.APP_ID_PARAM) appid: String
    ): GeocodingResult
}