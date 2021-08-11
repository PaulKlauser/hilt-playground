package com.example.flowplayground

import com.example.flowplayground.flowa.FlowAComponent
import com.example.flowplayground.list.ListFragment
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [AppModule::class, ApiModule::class])
interface AppComponent {

    fun inject(listActivity: ListFragment)

    @Component.Factory
    interface Factory {
        fun create(): AppComponent
    }

    fun flowAComponentFactory(): FlowAComponent.Factory

}