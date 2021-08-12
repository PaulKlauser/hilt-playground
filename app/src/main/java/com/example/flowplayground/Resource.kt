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

    data class Success<T>(val newData: T) : Resource<T>()
    data class Error<T>(val error: Throwable, val previousData: T?) : Resource<T>()
    //TODO: PK - even useful having Loading here, or too low level?
    data class Loading<T>(val previousData: T?) : Resource<T>()
    object Empty : Resource<Nothing>()
}