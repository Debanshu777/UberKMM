import SwiftUI
import shared
import KMPNativeCoroutinesAsync

struct ContentView: View {
    @ObservedObject var contentViewModel:ContentViewModel = ContentViewModel()

	var body: some View {
        UberMapViewRepresentable()
            .ignoresSafeArea()
    }
}

struct ContentView_Previews: PreviewProvider {
	static var previews: some View {
        ContentView(contentViewModel: ContentViewModel())
	}
}
