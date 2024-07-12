package com.aragones.sergio.kotlinexpert

import androidx.compose.ui.window.ComposeUIViewController
import com.aragones.sergio.kotlinexpert.ui.App
import platform.UIKit.UIViewController

@Suppress("unused", "FunctionName")
fun MainViewController(): UIViewController {
    return ComposeUIViewController {
        App()
    }
}