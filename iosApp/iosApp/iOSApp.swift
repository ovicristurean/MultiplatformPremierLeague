import SwiftUI
import shared

@main
struct iOSApp: App {

    init() {
    	KoinStarterKt.doInitKoin()
    }

	var body: some Scene {
		WindowGroup {
			ContentView()
		}
	}
}