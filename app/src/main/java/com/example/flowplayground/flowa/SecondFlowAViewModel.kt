package com.example.flowplayground.flowa

import androidx.lifecycle.ViewModel
import javax.inject.Inject

class SecondFlowAViewModel @Inject constructor(
    flowARepository: FlowARepository
) : ViewModel() {
    val text = flowARepository.text
}