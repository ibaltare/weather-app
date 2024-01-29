package com.navi.weather.model

typealias GeocodingResult = List<GeocodingElement>

data class GeocodingElement (
    val name: String,
    val lat: Double,
    val lon: Double,
    val country: String,
    val state: String? = null
)
