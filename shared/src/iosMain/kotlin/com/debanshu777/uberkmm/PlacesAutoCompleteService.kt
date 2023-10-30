package com.debanshu777.uberkmm

import com.rickclephas.kmp.nativecoroutines.NativeCoroutines
import platform.Foundation.NSError
import platform.MapKit.MKLocalSearchCompleter
import platform.MapKit.MKLocalSearchCompleterDelegateProtocol
import platform.MapKit.MKLocalSearchCompletion
import platform.darwin.NSObject
import kotlin.coroutines.resume
import kotlin.coroutines.resumeWithException
import kotlin.coroutines.suspendCoroutine

actual class PlacesAutoCompleteService {

    private val searchComplete = MKLocalSearchCompleter()
    private var resultList: List<AutoCompletePlacesData> = emptyList()

    private class PlacesAutoCompleteDelegate : NSObject(), MKLocalSearchCompleterDelegateProtocol {

        var onSearchResultUpdate: ((List<AutoCompletePlacesData>) -> Unit)? = null
        override fun completerDidUpdateResults(completer: MKLocalSearchCompleter) {
            val suggestion = completer.results.mapNotNull {
                val suggestion = it as MKLocalSearchCompletion
                AutoCompletePlacesData(
                    locationName = suggestion.title,
                    locationCoordinates = Location(0.0, 0.0)
                )
            }
            onSearchResultUpdate?.invoke(suggestion)
        }

        override fun completer(completer: MKLocalSearchCompleter, didFailWithError: NSError) {
            onSearchResultUpdate?.invoke(emptyList())
        }
    }

    @NativeCoroutines
    actual suspend fun getAutoSearchResult(placesQuery: String): List<AutoCompletePlacesData> =
        suspendCoroutine { continuation ->
            searchComplete.queryFragment = "Apple"
            val placesAutoCompleteDelegate = PlacesAutoCompleteDelegate()
            placesAutoCompleteDelegate.onSearchResultUpdate = { results ->
                try {
                    resultList = results
                    print("Async Success: $results")
                    continuation.resume(resultList)
                } catch (e: Exception) {
                    continuation.resumeWithException(Exception("Unable to get search response ${e.message}"))
                }
            }
            searchComplete.delegate = placesAutoCompleteDelegate
        }
}