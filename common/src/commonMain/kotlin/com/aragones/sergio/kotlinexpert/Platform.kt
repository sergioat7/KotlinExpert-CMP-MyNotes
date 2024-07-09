package com.aragones.sergio.kotlinexpert

expect fun getPlatformName(): String

fun getAppTitle(): String = "My Notes - ${getPlatformName()}"