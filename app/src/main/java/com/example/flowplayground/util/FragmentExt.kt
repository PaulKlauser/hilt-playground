package com.example.flowplayground.util

import androidx.annotation.MainThread
import androidx.fragment.app.Fragment
import androidx.fragment.app.createViewModelLazy
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelStoreOwner

@MainThread
inline fun <reified VM : ViewModel> Fragment.viewModels(
    noinline ownerProducer: () -> ViewModelStoreOwner = { this },
    noinline factoryProducer: (() -> VM)
): Lazy<VM> = createViewModelLazy(
    VM::class,
    { ownerProducer().viewModelStore },
    { createFactoryProvider(factoryProducer) })

fun <T : ViewModel?> createFactoryProvider(producer: () -> T): ViewModelProvider.Factory {
    return object : ViewModelProvider.Factory {
        override fun <T : ViewModel?> create(modelClass: Class<T>): T {
            return producer() as T
        }
    }
}
