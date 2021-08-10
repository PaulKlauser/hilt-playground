package com.example.flowplayground

sealed class Resource<out T> {

    open val data: T?
        get() {
            return when (this) {
                is Success -> newData
                is Error -> previousData
                is Loading -> previousData
                Empty -> null
            }
        }

    data class Success<T>(val newData: T) : Resource<T>() {
//        override val data = newData
    }
    data class Error<T>(val error: Throwable, val previousData: T?) : Resource<T>()
    data class Loading<T>(val previousData: T?) : Resource<T>()
    object Empty : Resource<Nothing>()
}