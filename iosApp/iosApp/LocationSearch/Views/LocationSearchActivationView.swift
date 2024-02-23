//
//  LocationSearchActivationView.swift
//  iosApp
//
//  Created by Debanshu Datta on 05/11/23.
//  Copyright © 2023 orgName. All rights reserved.
//

import SwiftUI

struct LocationSearchActivationView: View {
    var body: some View {
        HStack{
            Rectangle()
                .fill(Color.black)
                .frame(width: 8, height:8)
                .padding(.horizontal)
            Text("Where to?")
                .foregroundColor(Color(.darkGray))
            Spacer()
        }
        .frame(width: UIScreen.main.bounds.width-32, height: 50)
        .background(
            Rectangle().fill(Color.white).shadow(radius: 2))
    }
}

#Preview {
    LocationSearchActivationView()
}
