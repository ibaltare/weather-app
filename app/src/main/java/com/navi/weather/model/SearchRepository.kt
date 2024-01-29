package com.navi.weather.model

import com.navi.weather.BuildConfig
import com.navi.weather.utils.Constants

class SearchRepository {

    suspend fun getSearchResult(city: String) =
        RemoteConnection.service.getCoordinates(
            city,
            Constants.LIMIT_VALUE,
            BuildConfig.weather_api_key
        )

}