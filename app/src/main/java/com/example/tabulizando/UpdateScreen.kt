package com.example.tabulizando

import android.content.Context
import android.widget.Toast
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
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController

//Função da tela de alteração
@Composable
fun UpdateScreen(navController: NavController){
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
            text = "Altere um livro à coleção",
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
            Button(onClick = { updateBook(isbn.toInt(),title, author, publisher, description, imgUrl, dbHelper, context) }, colors = ButtonDefaults.buttonColors(
                Color.Red), shape = RoundedCornerShape(15)) {
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

fun updateBook(isbn: Int, title: String, author: String, publisher: String, description: String, imgUrl: String, dbHelper: DataBaseHelper, context: Context){
    return if (dbHelper.updateBook(isbn, title, author, publisher, description, imgUrl)){
        Toast.makeText(context, "Livro alterado com sucesso!", Toast.LENGTH_LONG).show()
    } else{
        Toast.makeText(context, "Livro não encontrado", Toast.LENGTH_LONG).show()
    }
}