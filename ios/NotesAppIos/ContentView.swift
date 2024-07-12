//
//  ContentView.swift
//  NotesAppIos
//
//  Created by Sergio Aragonés on 12/7/24.
//

import common
import SwiftUI

struct ContentView: View {
    var body: some View {
        VStack {
            Image(systemName: "globe")
                .imageScale(.large)
                .foregroundStyle(.tint)
            Text(PlatformKt.getAppTitle())
        }
        .padding()
    }
}

#Preview {
    ContentView()
}
