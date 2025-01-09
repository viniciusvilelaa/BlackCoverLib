package com.example.tabulizando

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.bumptech.glide.annotation.GlideModule
import com.bumptech.glide.integration.compose.ExperimentalGlideComposeApi
import com.bumptech.glide.integration.compose.GlideImage


@Composable
fun ListScreen(dbHelper: DataBaseHelper, navController: NavController){
    val books = remember { mutableStateOf(emptyList<Book>()) }

    LaunchedEffect(Unit) {
        books.value = dbHelper.listAll(dbHelper)
    }

    Column(modifier = Modifier.fillMaxSize()) {
        LazyColumn(
        ) {
            items(books.value){ book ->
                BookItem(book)
            }
        }

        Button(onClick = {backScreen(navController)}, colors = ButtonDefaults.buttonColors(Color.Red)) {
            Text(text = "Voltar",
                fontSize = 15.sp)
        }

    }

}



@OptIn(ExperimentalGlideComposeApi::class)
@Composable
fun BookItem(book: Book){
    Card(modifier = Modifier.fillMaxWidth()
        .clickable { println("CLICADO") }){
        Column {
            Column {
                GlideImage(model = "https://cdl-static.s3-sa-east-1.amazonaws.com/covers/gg/9788535928358/jantar-secreto.jpg", contentDescription = "Imagem de um livro")
            }

            Row {
                Text(text = book.title)
                Text(text = book.publisher)
                Text(text = book.isbn.toString())
            }

        }
    }
}


