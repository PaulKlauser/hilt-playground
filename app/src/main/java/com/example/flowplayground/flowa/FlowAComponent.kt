package com.example.flowplayground.flowa

import com.example.flowplayground.FlowScope
import dagger.Subcomponent

@Subcomponent
@FlowScope
interface FlowAComponent {

    fun inject(firstFlowAFragment: FirstFlowAFragment)

    fun inject(secondFlowAFragment: SecondFlowAFragment)

    fun flowRepository(): FlowARepository

    @Subcomponent.Factory
    interface Factory {
        fun create(): FlowAComponent
    }

}