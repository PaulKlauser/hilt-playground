package com.example.flowplayground.flowa

import androidx.lifecycle.ViewModel
import javax.inject.Inject

class SecondFlowAViewModel(
    flowARepository: FlowARepository
) : ViewModel() {
    val text = flowARepository.text

    class Factory @Inject constructor() {
        fun create(flowARepository: FlowARepository): SecondFlowAViewModel {
            return SecondFlowAViewModel(flowARepository)
        }
    }
}