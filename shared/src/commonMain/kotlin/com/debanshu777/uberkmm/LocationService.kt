package com.debanshu777.uberkmm

data class Location(val latitude: Double, val longitude: Double)
expect class LocationService() {
    suspend fun getCurrentLocation(): Location
}
