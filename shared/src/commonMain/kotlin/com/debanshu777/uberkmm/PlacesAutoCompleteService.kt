package com.debanshu777.uberkmm

data class AutoCompletePlacesData(val locationName: String, val locationCoordinates: Location)
expect class PlacesAutoCompleteService {
    suspend fun getAutoSearchResult(placesQuery: String): List<AutoCompletePlacesData>
}