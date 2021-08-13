package com.example.flowplayground.list

import com.example.flowplayground.Resource
import com.example.flowplayground.util.IdlingResource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.updateAndGet
import javax.inject.Inject

class TodoRepository @Inject constructor(
    private val service: PlaceHolderService,
    private val idlingResource: IdlingResource
) {

    private val todos: MutableStateFlow<Resource<List<Todo>>> = MutableStateFlow(Resource.Empty)

    suspend fun fetchTodos(): Resource<List<Todo>> {
        todos.value = Resource.Loading(todos.value.data)
        return todos.updateAndGet {
            idlingResource.taskStarted()
            try {
                Resource.Success(service.getTodos())
            } catch (e: Exception) {
                Resource.Error(e, todos.value.data)
            } finally {
                idlingResource.taskEnded()
            }
        }
    }

    fun getTodos(): Flow<Resource<List<Todo>>> {
        return todos
    }

    suspend fun fetchTodo(id: Int): Resource<DetailedTodo> {
        return try {
            Resource.Success(service.getTodo(id))
        } catch (e: Exception) {
            Resource.Error(e, null)
        }
    }

}