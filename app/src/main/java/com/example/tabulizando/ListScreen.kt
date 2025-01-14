package com.example.tabulizando

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.asPaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.statusBars
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CardElevation
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.scale
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.node.ModifierNodeElement
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import androidx.navigation.NavController
import com.bumptech.glide.annotation.GlideModule
import com.bumptech.glide.integration.compose.ExperimentalGlideComposeApi
import com.bumptech.glide.integration.compose.GlideImage

//Funçao da tela de listagem
@Composable
fun ListScreen(dbHelper: DataBaseHelper, navController: NavController){
    val books = remember { mutableStateOf(emptyList<Book>()) }

    LaunchedEffect(Unit) {
        books.value = dbHelper.listAll(dbHelper)
    }

    Column(modifier = Modifier.fillMaxSize()
        .padding(WindowInsets.statusBars.asPaddingValues()),
        horizontalAlignment = Alignment.CenterHorizontally) {
        Text(
            text = "Sua coleção de livros",
            fontSize = 20.sp,
            fontWeight = FontWeight(400),
            color = Color.Red
        )

        Spacer(modifier = Modifier.height(20.dp))

        LazyColumn(
        ) {
            items(books.value){ book ->
                BookItem(book)
            }
        }

        Button(onClick = {backScreen(navController)}, colors = ButtonDefaults.buttonColors(Color.Red),
            modifier = Modifier.fillMaxWidth()
                .padding(16.dp),
            shape = RoundedCornerShape(15)
        ) {
            Text(text = "Voltar",
                fontSize = 15.sp)
        }

    }

}


//Função composable para configurar como os livros vão ser apresentados na listagem
@OptIn(ExperimentalGlideComposeApi::class)
@Composable
fun BookItem(book: Book){
    var showDialog by remember { mutableStateOf(false) }
    var selectedBook by remember { mutableStateOf<Book?>(null) }

    Card(modifier = Modifier.fillMaxWidth()
        .clickable {
            selectedBook = book
            showDialog = true
        }
        .padding(16.dp),
        shape = RoundedCornerShape(15),
        elevation = CardDefaults.cardElevation(10.dp)){
        Row (modifier = Modifier.padding(10.dp)){
            Column {
                GlideImage(model = book.imgUrl, contentDescription = "Imagem de um livro", modifier = Modifier
                    .size(100.dp)
                    .scale(1.0f))
            }

            Row (verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.fillMaxSize()){
                Column {
                    Text(text = book.title,
                        style = TextStyle(fontSize = 22.sp))
                    Text(text = book.publisher,
                        fontStyle = FontStyle.Italic, fontSize = 18.sp)
                    Text(text = book.isbn.toString(), fontWeight = FontWeight.Bold)
                }

            }

        }
    }

    if(showDialog){
        Dialog(onDismissRequest = {showDialog = false}) {
            CardDetail(book)
        }

    }
}


