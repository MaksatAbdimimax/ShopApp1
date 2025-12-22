package com.geeks.shopapp1.ui.models


sealed interface UiState<out T> {
    data object Loaging : UiState<Nothing>
    data class Succes <T> (val  data: T) : UiState<T>
    data class Error (val message: String) : UiState<Nothing>
}
fun <T> someFunc (someValue: T) {
    someValue

}

fun func(){
    someFunc(true)
}
