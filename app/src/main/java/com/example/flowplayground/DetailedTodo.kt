package com.example.flowplayground

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class DetailedTodo(
    val id: String,
    val title: String
)