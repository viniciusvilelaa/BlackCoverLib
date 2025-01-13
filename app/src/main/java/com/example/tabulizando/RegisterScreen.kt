package com.example.tabulizando

import android.content.Context
import android.provider.ContactsContract.Data
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Shapes
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController


@Composable
fun RegisterScreen(navController: NavController){

    val context = LocalContext.current
    val dbHelper = remember { DataBaseHelper(context) }

    var title by remember {
        mutableStateOf("")
    }
    var author by remember {
        mutableStateOf("")
    }
    var publisher by remember {
        mutableStateOf("")
    }

    var isbn by remember {
        mutableStateOf("")
    }

    var description by remember {
        mutableStateOf("")
    }

    var imgUrl by remember {
        mutableStateOf("")
    }




    Column(modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally){
        Text(
            text = "Adicione um livro à coleção",
            fontSize = 20.sp,
            fontWeight = FontWeight(400),
            color = Color.Red
        )

        Spacer(modifier = Modifier.height(20.dp))

        OutlinedTextField(value = title, onValueChange = {title = it}, label = { Text(text = "Title") })

        Spacer(modifier = Modifier.height(20.dp))

        OutlinedTextField(value = author, onValueChange = {author = it}, label = { Text(text = "Author") })

        Spacer(modifier = Modifier.height(20.dp))

        OutlinedTextField(value = publisher, onValueChange = {publisher = it}, label = { Text(text = "Publisher") })

        Spacer(modifier = Modifier.height(20.dp))

        OutlinedTextField(value = isbn, onValueChange = {isbn = it}, label = { Text(text = "Isbn") })

        Spacer(modifier = Modifier.height(20.dp))

        OutlinedTextField(value = description, onValueChange = {if (it.length <= 100){
            description = it
        } }, label = { Text(text = "Description") })

        Spacer(modifier = Modifier.height(20.dp))

        OutlinedTextField(value = imgUrl, onValueChange = {imgUrl = it}, label = { Text(text = "Url") })

        Spacer(modifier = Modifier.height(20.dp))




        Row {
            Button(onClick = { saveBook(dbHelper, navController, title, author, publisher, isbn.toInt(), description, imgUrl)}, colors = ButtonDefaults.buttonColors(Color.Red),
                shape = RoundedCornerShape(15)
            ) {
                Text(text = "Salvar",
                    fontSize = 15.sp)
            }

            Spacer(modifier = Modifier.width(10.dp))

            Button(onClick = {backScreen(navController)}, colors = ButtonDefaults.buttonColors(Color.Red),
                shape = RoundedCornerShape(15)) {
                Text(text = "Voltar",
                    fontSize = 15.sp)
            }
        }

    }
}

fun saveBook(dbHelper: DataBaseHelper, navController: NavController, title: String, author: String, publisher: String, isbn: Int, description: String, url: String){
    val book = Book(title, author, publisher, isbn, description, url)
    dbHelper.saveBook(dbHelper,book)
    backScreen(navController)
}


fun backScreen(navController: NavController){

    navController.navigate("MenuScreen")
}