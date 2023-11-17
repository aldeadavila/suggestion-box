package com.aldeadavila.suggestionbox.domain.util

sealed class Resource<out T> {

    object Loading: Resource<Nothing>()
    data class Succes<out T>(val data: T): Resource<T>()
    data class Failure<out T>(val message: String): Resource<T>()

}
