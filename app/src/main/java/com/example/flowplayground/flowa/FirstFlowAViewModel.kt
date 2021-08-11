package com.example.flowplayground.flowa

import androidx.lifecycle.ViewModel
import com.example.flowplayground.list.PlaceHolderService
import javax.inject.Inject

class FirstFlowAViewModel @Inject constructor(
    private val flowARepository: FlowARepository,
    private val service: PlaceHolderService
) : ViewModel() {

    val text = flowARepository.text

    fun setText(text: String) = flowARepository.setText(text)

}