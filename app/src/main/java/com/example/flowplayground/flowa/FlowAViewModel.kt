package com.example.flowplayground.flowa

import androidx.lifecycle.ViewModel

class FlowAViewModel : ViewModel() {

    val flowAComponent: FlowAComponent = DaggerFlowAComponent.create()

}