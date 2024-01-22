package com.navi.weather.model

import android.annotation.SuppressLint
import android.app.Activity
import android.location.Location
import com.google.android.gms.location.LocationServices
import kotlinx.coroutines.suspendCancellableCoroutine
import kotlin.coroutines.resume

class LocationDataSource(activity: Activity) {

    private val fusedLocationClient = LocationServices.getFusedLocationProviderClient(activity)
    @SuppressLint("MissingPermission")
    suspend fun getCurrentLocation(): Location? =
        suspendCancellableCoroutine { continuation ->
            fusedLocationClient.lastLocation
                .addOnCompleteListener {
                    continuation.resume(it.result)
                }
        }
}