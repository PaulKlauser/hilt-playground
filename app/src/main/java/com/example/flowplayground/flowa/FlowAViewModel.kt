package com.example.flowplayground.flowa

import androidx.lifecycle.AbstractSavedStateViewModelFactory
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.savedstate.SavedStateRegistryOwner
import com.example.flowplayground.AppComponent
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

private const val STATE_KEY = "state"

class FlowAViewModel(
    appComponent: AppComponent,
    savedStateHandle: SavedStateHandle
) : ViewModel() {

    val flowAComponent: FlowAComponent = appComponent
        .flowAComponentFactory()
        .create()

    init {
        flowAComponent.flowRepository().setState(savedStateHandle[STATE_KEY])
        viewModelScope.launch {
            flowAComponent.flowRepository().state().collect {
                savedStateHandle[STATE_KEY] = it
            }
        }
    }

    class Factory constructor(
        private val appComponent: AppComponent,
        owner: SavedStateRegistryOwner
    ) : AbstractSavedStateViewModelFactory(owner, null) {
        override fun <T : ViewModel?> create(
            key: String,
            modelClass: Class<T>,
            handle: SavedStateHandle
        ): T {
            return FlowAViewModel(appComponent, handle) as T
        }
    }

}