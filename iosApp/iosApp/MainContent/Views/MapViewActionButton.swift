//
//  MapViewActionButton.swift
//  iosApp
//
//  Created by Debanshu Datta on 05/11/23.
//  Copyright © 2023 orgName. All rights reserved.
//

import SwiftUI

struct MapViewActionButton: View {
    @Binding var showLocationSearchview: Bool
    var body: some View {
        Button{
            withAnimation(.spring()){
                showLocationSearchview.toggle()
            }
        }label: {
            Image(systemName: showLocationSearchview ? "arrow.left" : "line.3.horizontal")
                .frame(width: 20, height: 20)
                .font(.title2)
                .foregroundColor(.black)
                .padding()
                .background(Color.white)
                .clipShape(/*@START_MENU_TOKEN@*/Circle()/*@END_MENU_TOKEN@*/)
                .shadow(radius: 2)
        }
        .frame(maxWidth: .infinity,alignment: .leading)
    }
}

#Preview {
    MapViewActionButton(showLocationSearchview: .constant(true))
}
