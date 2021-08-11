package com.example.flowplayground.list

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.flowplayground.Resource
import kotlinx.coroutines.async
import kotlinx.coroutines.awaitAll
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

class ListViewModel @Inject constructor(
    private val repository: TodoRepository
) : ViewModel() {

    private val detailedTodos : MutableStateFlow<Resource<List<DetailedTodo>>> = MutableStateFlow(
        Resource.Empty
    )

    fun getTodos(): Flow<Resource<List<DetailedTodo>>> {
        return detailedTodos
    }

    /**
     * Example of composing multiple one-shot calls using suspend funs
     */
    fun fetchTodos() {
        viewModelScope.launch {
            repository.fetchTodos().let { ids ->
                if (ids is Resource.Success) {
                    detailedTodos.value = Resource.Success(ids.newData.map {
                        async { repository.fetchTodo(it.id).data }
                    }.awaitAll().filterNotNull())
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