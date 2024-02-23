//
//  LocationSearchViewModel.swift
//  iosApp
//
//  Created by Debanshu Datta on 05/11/23.
//  Copyright © 2023 orgName. All rights reserved.
//

import Foundation
import MapKit

class LocationSearchViewModel:NSObject, ObservableObject{
    @Published var results=[MKLocalSearchCompletion]()
    private let searchCompleter=MKLocalSearchCompleter()
    var querryFragment:String=""{
        didSet{
            searchCompleter.queryFragment=querryFragment
        }
    }
    
    override init() {
        super.init()
        searchCompleter.delegate=self
        searchCompleter.queryFragment=querryFragment
    }
}

extension LocationSearchViewModel:MKLocalSearchCompleterDelegate{
    func completerDidUpdateResults(_ completer: MKLocalSearchCompleter) {
        self.results=completer.results
    }
}
