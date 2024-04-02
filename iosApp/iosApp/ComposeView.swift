//
//  ComposeView.swift
//  iosApp
//
//  Created by Ovidiu Cristurean on 22.02.2024.
//  Copyright © 2024 orgName. All rights reserved.
//

import Foundation
import shared
import SwiftUI

struct ComposeView: UIViewControllerRepresentable {
    func updateUIViewController(_ uiViewController: UIViewControllerType, context: Context) {

    }
    
    func makeUIViewController(context: Context) -> some UIViewController {
        MainViewControllerKt.MainViewController()
    }
}
