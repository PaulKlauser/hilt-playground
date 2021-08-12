package com.example.flowplayground.flowa

import androidx.lifecycle.ViewModel
import com.example.flowplayground.list.PlaceHolderService
import javax.inject.Inject

class FirstFlowAViewModel(
    private val flowARepository: FlowARepository,
    private val service: PlaceHolderService
) : ViewModel() {

    val text = flowARepository.text

    fun setText(text: String) = flowARepository.setText(text)

    class Factory @Inject constructor(private val service: PlaceHolderService) {
        fun create(flowARepository: FlowARepository): FirstFlowAViewModel {
            return FirstFlowAViewModel(flowARepository, service)
        }
    }

}