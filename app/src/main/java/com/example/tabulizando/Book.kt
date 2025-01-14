package com.example.tabulizando

//Classe base para o objeto Livro
data class Book(
    val title: String,
    val author: String,
    val publisher: String,
    val isbn: Int, // Chave primária
    val description: String,
    val imgUrl: String
)
