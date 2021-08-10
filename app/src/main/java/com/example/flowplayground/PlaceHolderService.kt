package com.example.flowplayground

import retrofit2.http.GET
import retrofit2.http.Path

interface PlaceHolderService {

    @GET("todos")
    suspend fun getTodos(): List<Todo>

    @GET("todos/{id}")
    suspend fun getTodo(@Path("id") id: Int): DetailedTodo

}