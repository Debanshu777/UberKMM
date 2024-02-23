//
//  ContentViewModel.swift
//  iosApp
//
//  Created by Debanshu Datta on 23/10/23.
//  Copyright © 2023 orgName. All rights reserved.
//

import Foundation
import shared
import KMPNativeCoroutinesAsync
import MapKit

@MainActor
final class ContentViewModel: ObservableObject {

    @Published var location: Location = Location(latitude: 0.0, longitude: 0.0)

    init() {
        fetchData()
    }

    func fetchData() {
        Task {
            let value = await asyncResult(for: LocationService().getCurrentLocation())
            switch value {
            case .success(let result):
                print("Async Success: \(result)")
                self.location = result
            case .failure(let error):
                print("Async Failed with error: \(error)")
            }
        }
    }
}
