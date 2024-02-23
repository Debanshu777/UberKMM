//
//  UberMapViewRepresentable.swift
//  iosApp
//
//  Created by Debanshu Datta on 22/10/23.
//  Copyright © 2023 orgName. All rights reserved.
//

import SwiftUI
import MapKit
import shared

struct UberMapViewRepresentable: UIViewRepresentable{
   
    let mapView=MKMapView()
    let locationManager = LocationService()
    
    func makeUIView(context: Context) -> some UIView {
        mapView.delegate = context.coordinator
        mapView.isRotateEnabled = false
        mapView.showsUserLocation = true
        mapView.userTrackingMode = .follow
        return mapView
    }
    
    func updateUIView(_ uiView: UIViewType, context: Context) {
        
    }

    func makeCoordinator() -> MapCoordinates {
        return MapCoordinates(parent: self)
    }
}

extension UberMapViewRepresentable {
    class MapCoordinates:NSObject,MKMapViewDelegate {
        let parent: UberMapViewRepresentable
        init(parent: UberMapViewRepresentable) {
            self.parent = parent
            super.init()
        }
        func mapView(_ mapView: MKMapView, didUpdate userLocation: MKUserLocation) {
            let region = MKCoordinateRegion(
                center: CLLocationCoordinate2D(latitude: userLocation.coordinate.latitude, longitude: userLocation.coordinate.longitude), span: MKCoordinateSpan(latitudeDelta: 0.05, longitudeDelta: 0.05))
            parent.mapView.setRegion(region, animated: true)
        }
    }
}
