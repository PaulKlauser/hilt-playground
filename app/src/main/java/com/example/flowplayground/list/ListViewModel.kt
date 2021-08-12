package com.example.flowplayground.list

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.flowplayground.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.async
import kotlinx.coroutines.awaitAll
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ListViewModel @Inject constructor(
    private val repository: TodoRepository
) : ViewModel() {

    private val _detailedTodos: MutableStateFlow<List<DetailedTodo>?> = MutableStateFlow(
        null
    )
    val detailedTodos: Flow<List<DetailedTodo>?> = _detailedTodos
    private val _isLoading: MutableStateFlow<Boolean> = MutableStateFlow(false)
    val isLoading: Flow<Boolean> = _isLoading
    private val _error = MutableStateFlow(false)
    val error: Flow<Boolean> = _error

    /**
     * Example of composing multiple one-shot calls using suspend funs
     */
    fun fetchTodos() {
        viewModelScope.launch {
            _isLoading.value = true
            repository.fetchTodos().let { ids ->
                if (ids is Resource.Success) {
                    _detailedTodos.value = ids.newData.map { loadedId ->
                        async {
                            when (val detailedTodo = repository.fetchTodo(loadedId.id)) {
                                is Resource.Success -> {
                                    detailedTodo.data
                                }
                                is Resource.Error -> {
                                    _error.value = true
                                    null
                                }
                                else -> {
                                    null
                                }
                            }
                        }
                    }.awaitAll()
                        .filterNotNull()
                    _isLoading.value = false
                } else if (ids is Resource.Error) {
                    _error.value = true
                }
            }
        }
    }
// TODO: PK - Don't need this if using Evan's lib
//    class Factory @Inject constructor(
//        private val repository: TodoRepository
//    ) : ViewModelProvider.Factory {
//        override fun <T : ViewModel?> create(modelClass: Class<T>): T {
//            return MainViewModel(repository) as T
//        }
//    }

}