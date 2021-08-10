package com.example.flowplayground.flowa

import androidx.lifecycle.ViewModel
import com.example.flowplayground.FlowRepository
import javax.inject.Inject

class FirstFlowAViewModel @Inject constructor(
    private val flowRepository: FlowRepository
) : ViewModel() {

    var text = flowRepository.text
        set(value) {
            flowRepository.text = value
            field = value
        }

}