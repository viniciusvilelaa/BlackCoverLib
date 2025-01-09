package com.example.tabulizando

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview


@Composable
fun ListScreen(dbHelper: DataBaseHelper){
    val books = remember { mutableStateOf(emptyList<Book>()) }

    LaunchedEffect(Unit) {
        books.value = dbHelper.listAll(dbHelper)
    }

    LazyColumn(modifier =Modifier.fillMaxSize()) {
        items(books.value){ book ->
            BookItem(book)
        }
    }


}


@Composable
fun BookItem(book: Book){
    Card(modifier = Modifier.fillMaxWidth()){
        Column {
            Text(text = book.title)
            Text(text = book.author)
        }
    }
}
