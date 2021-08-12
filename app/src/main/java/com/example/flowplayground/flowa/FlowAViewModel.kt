package com.example.flowplayground.flowa

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class FlowAViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle,
    val flowARepository: FlowARepository
) : ViewModel() {

    init {
        viewModelScope.launch {
             flowARepository.state().collect {
                savedStateHandle[FLOW_REPO_STATE_KEY] = it
            }
        }
    }

}