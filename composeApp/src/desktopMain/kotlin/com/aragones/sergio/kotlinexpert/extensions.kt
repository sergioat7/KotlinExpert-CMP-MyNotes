package com.aragones.sergio.kotlinexpert

import kotlinx.coroutines.flow.MutableStateFlow

fun <T> MutableStateFlow<T>.update(produceValue: (T) -> T) {
    this.value = produceValue(this.value)
}