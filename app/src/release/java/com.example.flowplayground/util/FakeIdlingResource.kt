package com.example.flowplayground.util

import javax.inject.Inject

class FakeIdlingResource @Inject constructor() : IdlingResource {
    override fun taskStarted() {
    }

    override fun taskEnded() {
    }
}