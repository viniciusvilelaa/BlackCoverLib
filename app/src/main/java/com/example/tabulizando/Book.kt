package com.example.tabulizando

data class Book(
    val title: String,
    val author: String,
    val publisher: String,
    val isbn: Int, // Chave primária
    val description: String,
    val imgUrl: String
)
