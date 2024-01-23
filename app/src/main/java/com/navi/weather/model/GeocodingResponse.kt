package com.navi.weather.model

data class GeocodingResponse(val uno:String)

typealias Geocoding = ArrayList<GeocodingElement>

data class GeocodingElement (
    val name: String,
    val localNames: Map<String, String>? = null,
    val lat: Double,
    val lon: Double,
    val country: String,
    val state: String
)
