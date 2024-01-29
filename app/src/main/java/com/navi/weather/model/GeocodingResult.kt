package com.navi.weather.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

typealias GeocodingResult = List<GeocodingElement>

@Parcelize
data class GeocodingElement (
    val name: String,
    val lat: Double,
    val lon: Double,
    val country: String,
    val state: String? = null
) : Parcelable
