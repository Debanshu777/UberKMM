//
//  LocationSearchView.swift
//  iosApp
//
//  Created by Debanshu Datta on 05/11/23.
//  Copyright © 2023 orgName. All rights reserved.
//

import SwiftUI

struct LocationSearchView: View {
    @State private var startLocationText=""
    @State private var destinationLocationText=""
    @StateObject var viewModel = LocationSearchViewModel()
    
    var body: some View {
        VStack{
            HStack{
                VStack{
                    Circle()
                        .fill(.gray)
                        .frame(width:6,height: 6)
                    Rectangle()
                        .fill(.gray)
                        .frame(width:1,height: 24)
                    Rectangle()
                        .fill(.black)
                        .frame(width:6,height: 6)
                }
                VStack{
                    TextField(" Current Location",text: $startLocationText)
                        .frame(height: 32)
                       .background(Color(.systemGroupedBackground))
                    
                    TextField(" Where to?",text: $viewModel.querryFragment)
                        .frame(height: 32)
                       .background(Color(.systemGray4))

                }

            }
            .padding(.horizontal)
            .padding(.top,60)
            
            ScrollView{
                VStack{
                    ForEach(viewModel.results,id: \.self){ result in
                        LocationSearchViewItem(title: result.title, subtitle: result.subtitle)
                    }
                }
            }
        }.background(.white)
    }
}

#Preview {
    LocationSearchView()
}
