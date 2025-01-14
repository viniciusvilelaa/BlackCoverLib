package com.example.tabulizando

import android.content.Context
import android.widget.Toast
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
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
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController

//Função da tela de Delete
@Composable
fun DeleteScreen(dbHelper: DataBaseHelper,navController: NavController, context: Context){

    var isbn by remember {
        mutableStateOf("")
    }


    Column(modifier = Modifier.fillMaxSize(), horizontalAlignment =
    Alignment.CenterHorizontally, verticalArrangement = Arrangement.Center){
        Text(
            text = "Delete um livro da sua coleção",
            fontSize = 20.sp,
            fontWeight = FontWeight(400),
            color = Color.Red
        )

        Spacer(modifier = Modifier.height(20.dp))

        OutlinedTextField(value = isbn, onValueChange = {isbn = it}, label = { Text(text = "Isbn") })

        Spacer(modifier = Modifier.height(20.dp))

        Row {
            Button(onClick = { deleteBook(isbn.toInt(), context, dbHelper,navController) }, colors = ButtonDefaults.buttonColors(Color.Red),
                shape = RoundedCornerShape(15)) {
                Text(text = "Excluir",
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

//Chamada da função deleteByIsbn
fun deleteBook(isbn: Int,context: Context, dbHelper: DataBaseHelper, navController: NavController){
    return if(dbHelper.deleteByIsbn(isbn)){
        Toast.makeText(context, "Livro excluído com sucesso", Toast.LENGTH_LONG).show()
        backScreen(navController)
    }else{
        Toast.makeText(context, "Livro não encontrado", Toast.LENGTH_LONG).show()
    }

}