package com.example.flowplayground.flowa

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject

private const val STATE_KEY = "state"

@HiltViewModel
class FlowAViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle,
    val flowARepository: FlowARepository
) : ViewModel() {

    init {
        flowARepository.setState(savedStateHandle[STATE_KEY])
        viewModelScope.launch {
             flowARepository.state().collect {
                savedStateHandle[STATE_KEY] = it
            }
        }
    }

}