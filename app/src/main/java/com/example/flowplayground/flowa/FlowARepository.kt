package com.example.flowplayground.flowa

import android.os.Parcelable
import dagger.hilt.android.scopes.ViewModelScoped
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.map
import kotlinx.parcelize.Parcelize
import javax.inject.Inject

@ViewModelScoped
class FlowARepository @Inject constructor() {

    private val _text = MutableStateFlow("")
    val text: Flow<String> = _text

    fun setText(text: String) {
        _text.value = text
    }

    fun setState(state: State?) {
        if (state == null) {
            return
        }
        _text.value = state.text
    }

    fun state(): Flow<State> {
        return text.map { State(it) }
    }

    @Parcelize
    data class State(
        val text: String
    ) : Parcelable

}