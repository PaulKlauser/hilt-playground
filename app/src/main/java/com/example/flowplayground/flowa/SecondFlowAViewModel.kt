package com.example.flowplayground.flowa

import androidx.lifecycle.ViewModel
import com.example.flowplayground.FlowRepository
import javax.inject.Inject

class SecondFlowAViewModel @Inject constructor(
    flowRepository: FlowRepository
) : ViewModel() {
    val text = flowRepository.text
}