//
//  LocationSearchViewItem.swift
//  iosApp
//
//  Created by Debanshu Datta on 05/11/23.
//  Copyright © 2023 orgName. All rights reserved.
//

import SwiftUI

struct LocationSearchViewItem: View {
    let title:String
    let subtitle:String
    var body: some View {
        HStack{
            Image(systemName: "mappin.circle.fill")
                .resizable()
                .foregroundColor(/*@START_MENU_TOKEN@*/.blue/*@END_MENU_TOKEN@*/)
                .accentColor(.white)
                .frame(width:40, height: 40)
            VStack(alignment: .leading, spacing: 4){
                Text(title)
                    .font(.body)
                Text(subtitle)
                    .font(.system(size: 15))
                    .foregroundStyle(.gray)
                Divider()
            }
            .padding(.horizontal,8)
            .padding(.vertical,8)
        }
        .padding(.horizontal)
    }
}

#Preview {
    LocationSearchViewItem(title: "Dummy Title", subtitle: "Dummy Subtitle")
}
