package com.example.flowplayground.baseadapter

interface ViewHolderPresenter<in T> {
    //TODO: PK - Perhaps remove?
    fun bind(data: T)
    fun bind(data: T, position: Int) {
        bind(data)
    }
    fun bind(data: T, position: Int, payloads: List<Any>) {
        bind(data, position)
    }
}