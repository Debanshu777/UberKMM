import SwiftUI
import shared
import KMPNativeCoroutinesAsync

struct ContentView: View {
    @ObservedObject var contentViewModel:ContentViewModel = ContentViewModel()

    @State private var showLocationSearchview = false
	var body: some View {
        ZStack(alignment: .top) {
            UberMapViewRepresentable()
                .ignoresSafeArea()
            if showLocationSearchview {
                LocationSearchView()
            } else {
                LocationSearchActivationView()
                    .padding(.top,60).onTapGesture {
                        withAnimation(.spring()){
                            showLocationSearchview.toggle()
                    }
                }
            }
            MapViewActionButton(showLocationSearchview: $showLocationSearchview)
                .padding(.leading,8)
        }
    }
}

struct ContentView_Previews: PreviewProvider {
	static var previews: some View {
        ContentView(contentViewModel: ContentViewModel())
	}
}
