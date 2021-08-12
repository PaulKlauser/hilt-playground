package com.example.flowplayground.flowa

import android.os.Parcelable
import dagger.hilt.android.scopes.ViewModelScoped
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.map
import kotlinx.parcelize.Parcelize
import javax.inject.Inject

const val FLOW_REPO_STATE_KEY = "flow_state"

// Don't need these annotations because of the @Provides in FlowAModule
// Is keeping these here informative or misleading?
@ViewModelScoped
class FlowARepository @Inject constructor(
    initialState: State?
) {

    private val _text = MutableStateFlow(initialState?.text.orEmpty())
    val text: Flow<String> = _text

    fun setText(text: String) {
        _text.value = text
    }

    fun state(): Flow<State> {
        return text.map { State(it) }
    }

    @Parcelize
    data class State(
        val text: String
    ) : Parcelable

}