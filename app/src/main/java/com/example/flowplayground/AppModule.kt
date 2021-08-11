package com.example.flowplayground

import com.example.flowplayground.flowa.FlowAComponent
import dagger.Module

@Module(subcomponents = [FlowAComponent::class])
class AppModule