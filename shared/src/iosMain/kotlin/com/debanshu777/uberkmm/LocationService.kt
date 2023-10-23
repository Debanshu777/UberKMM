package com.debanshu777.uberkmm

import com.rickclephas.kmp.nativecoroutines.NativeCoroutines
import kotlinx.cinterop.ExperimentalForeignApi
import kotlinx.cinterop.useContents
import platform.CoreLocation.CLLocation
import platform.CoreLocation.CLLocationManager
import platform.CoreLocation.CLLocationManagerDelegateProtocol
import platform.CoreLocation.kCLDistanceFilterNone
import platform.CoreLocation.kCLLocationAccuracyBest
import platform.Foundation.NSError
import platform.darwin.NSObject
import kotlin.concurrent.AtomicReference
import kotlin.coroutines.resume
import kotlin.coroutines.resumeWithException
import kotlin.coroutines.suspendCoroutine

actual class LocationService {
    // Define a native CLLocationManager object
    private val locationManager = CLLocationManager()

    // Define an atomic reference to store the latest location
    private val latestLocation = AtomicReference<Location?>(null)

    private class LocationDelegate : NSObject(), CLLocationManagerDelegateProtocol {

        // Define a callback to receive location updates
        var onLocationUpdate: ((Location?) -> Unit)? = null

        @OptIn(ExperimentalForeignApi::class)
        override fun locationManager(manager: CLLocationManager, didUpdateLocations: List<*>) {
            didUpdateLocations.firstOrNull()?.let {
                val location = it as CLLocation
                location.coordinate.useContents {
                    onLocationUpdate?.invoke(Location(latitude, longitude))
                }
            }
        }

        override fun locationManager(manager: CLLocationManager, didFailWithError: NSError) {
            onLocationUpdate?.invoke(null)
        }
    }

    // Implement the getCurrentLocation() method
    @NativeCoroutines
    actual suspend fun getCurrentLocation(): Location = suspendCoroutine { continuation ->
        locationManager.requestWhenInUseAuthorization()
        locationManager.desiredAccuracy = kCLLocationAccuracyBest
        locationManager.distanceFilter = kCLDistanceFilterNone
        locationManager.startUpdatingLocation()

        val locationDelegate = LocationDelegate()
        locationDelegate.onLocationUpdate = { location ->
            locationManager.stopUpdatingLocation()
            latestLocation.value = location
            if (location != null) {
                continuation.resume(location)
            } else {
                continuation.resumeWithException(Exception("Unable to get current location"))
            }
        }
        locationManager.delegate = locationDelegate

    }
}
