package com.debanshu777.uberkmm.android.features.map_view.views

import android.Manifest
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import com.debanshu777.uberkmm.Location
import com.debanshu777.uberkmm.LocationService
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.model.CameraPosition
import com.google.android.gms.maps.model.LatLng
import com.google.maps.android.compose.CameraPositionState
import com.google.maps.android.compose.GoogleMap
import com.google.maps.android.compose.MapProperties
import com.google.maps.android.compose.MapUiSettings
import com.google.maps.android.compose.rememberCameraPositionState
import dev.shreyaspatil.permissionflow.compose.rememberPermissionFlowRequestLauncher
import dev.shreyaspatil.permissionflow.compose.rememberPermissionState
import kotlinx.coroutines.launch

val permissionList = listOf(Manifest.permission.ACCESS_FINE_LOCATION)

@Composable
fun UberMapView() {
    val coroutineScope = rememberCoroutineScope()
    val permissionLauncher = rememberPermissionFlowRequestLauncher()
    val state by rememberPermissionState(Manifest.permission.ACCESS_FINE_LOCATION)
    var currentLocation: Location? by remember { mutableStateOf(null) }
    val cameraPositionState: CameraPositionState = rememberCameraPositionState()

    LaunchedEffect(state) {
        permissionLauncher.launch(permissionList.toTypedArray())
        coroutineScope.launch {
            currentLocation = if (state.isGranted) {
                LocationService().getCurrentLocation()
            } else {
                null
            }
        }
    }

    LaunchedEffect(currentLocation) {
        if (currentLocation != null) {
            cameraPositionState.animate(
                update = CameraUpdateFactory.newCameraPosition(
                    CameraPosition(
                        LatLng(
                            currentLocation!!.latitude,
                            currentLocation!!.longitude
                        ), 13f, 0f, 0f
                    )
                )
            )
        }
    }

    Column {
        GoogleMap(
            modifier = Modifier.fillMaxSize(),
            uiSettings = MapUiSettings(zoomControlsEnabled = true),
            cameraPositionState = cameraPositionState,
            properties = MapProperties(
                isMyLocationEnabled = true
            )
        )
    }

}