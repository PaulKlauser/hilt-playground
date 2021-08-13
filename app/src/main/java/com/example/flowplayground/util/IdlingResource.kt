package com.example.flowplayground.util

interface IdlingResource {
    fun taskStarted()
    fun taskEnded()
}