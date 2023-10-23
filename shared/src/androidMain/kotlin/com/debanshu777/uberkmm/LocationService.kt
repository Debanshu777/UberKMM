package com.debanshu777.uberkmm

import android.annotation.SuppressLint
import com.google.android.gms.location.LocationServices
import kotlin.coroutines.resume
import kotlin.coroutines.resumeWithException
import kotlin.coroutines.suspendCoroutine

actual class LocationService {

    // Initialize the FusedLocationProviderClient
    private val fusedLocationClient by lazy {
        LocationServices.getFusedLocationProviderClient(applicationContext)
    }

    // Implement the getCurrentLocation() method
    @SuppressLint("MissingPermission") // Assuming location permission check is already handled
    actual suspend fun getCurrentLocation(): Location = suspendCoroutine { continuation ->
        fusedLocationClient.lastLocation.addOnSuccessListener { location ->
            location?.let {
                continuation.resume(Location(it.latitude, it.longitude))
            } ?: run {
                continuation.resumeWithException(Exception("Unable to get current location"))
            }
        }.addOnFailureListener { e ->
            continuation.resumeWithException(e)
        }
    }
}