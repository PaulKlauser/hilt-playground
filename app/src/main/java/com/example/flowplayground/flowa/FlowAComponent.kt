package com.example.flowplayground.flowa

import com.example.flowplayground.FlowScope
import dagger.Component

@Component
@FlowScope
interface FlowAComponent {

    fun inject(firstFlowAFragment: FirstFlowAFragment)

    fun inject(secondFlowAFragment: SecondFlowAFragment)

//    @Component.Factory
//    interface Factory {
//        fun create(): FlowAComponent
//    }

}