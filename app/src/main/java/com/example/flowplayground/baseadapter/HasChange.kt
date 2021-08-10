package com.example.flowplayground.baseadapter

interface HasChange<T> {

    fun getChangePayload(oldItem: T): Any?

}