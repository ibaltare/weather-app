package com.navi.weather.model

import android.Manifest
import android.annotation.SuppressLint
import android.location.Location
import androidx.appcompat.app.AppCompatActivity
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices
import kotlinx.coroutines.suspendCancellableCoroutine
import kotlin.coroutines.resume

class LocationRepository(application: AppCompatActivity) {

    private val coarsePermissionChecker = PermissionChecker(
        application,
        Manifest.permission.ACCESS_COARSE_LOCATION
    )

    private val locationDataSource: LocationDataSource = LocationDataSource(application)

    @SuppressLint("MissingPermission")
    suspend fun getCurrentLocation(): Location? {
        val success = coarsePermissionChecker.request()
        return if (success) locationDataSource.getCurrentLocation() else null
    }
}