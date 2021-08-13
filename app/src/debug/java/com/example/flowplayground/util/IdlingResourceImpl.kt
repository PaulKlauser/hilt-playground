package com.example.flowplayground.util

import androidx.test.espresso.idling.CountingIdlingResource
import javax.inject.Inject

class IdlingResourceImpl @Inject constructor(): IdlingResource {

    private val countingIdlingResource = CountingIdlingResource("idlingResource")

    override fun taskStarted() {
        countingIdlingResource.increment()
    }

    override fun taskEnded() {
        countingIdlingResource.decrement()
    }
}