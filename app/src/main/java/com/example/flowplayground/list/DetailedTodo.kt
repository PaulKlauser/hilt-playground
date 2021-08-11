package com.example.flowplayground.list

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class DetailedTodo(
    val id: String,
    val title: String
)