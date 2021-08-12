package com.example.flowplayground.flowa

import androidx.lifecycle.SavedStateHandle
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped

@Module
@InstallIn(ViewModelComponent::class)
class FlowAModule {

    @Provides
    @ViewModelScoped
    fun flowARepository(savedStateHandle: SavedStateHandle): FlowARepository {
        return FlowARepository(savedStateHandle[FLOW_REPO_STATE_KEY])
    }

}