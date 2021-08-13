package com.example.flowplayground.util

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class IdlingResourceModule {
    @Binds
    abstract fun idlingResource(idlingResourceImpl: IdlingResourceImpl) : IdlingResource
}