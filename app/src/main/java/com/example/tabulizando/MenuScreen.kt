package com.example.tabulizando

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController

//Função da tela de menu
@Composable
fun MenuScreen(navController: NavController){
    Column(modifier = Modifier.fillMaxSize(),verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally) {

        Text(text = "Menu black cover",
            fontSize = 20.sp)

        Spacer(modifier = Modifier.height(10.dp))

        Row (){
            Button(onClick = {navController.navigate("RegisterScreen")}, colors = ButtonDefaults.buttonColors(
                Color.Red), shape = RoundedCornerShape(15), modifier = Modifier.size(width = 150.dp, height = 50.dp)
            ) {
                Text(text = "Cadastrar livro")
            }

            Spacer(modifier = Modifier.width(10.dp))

            Button(onClick = {navController.navigate("ListScreen")}, colors = ButtonDefaults.buttonColors(
                Color.Red), shape = RoundedCornerShape(15), modifier = Modifier.size(width = 150.dp, height = 50.dp)) {
                Text(text = "Listar livros")
            }
        }

        Spacer(modifier = Modifier.height(10.dp))

        Row {
            Button(onClick = {navController.navigate("DeleteScreen")}, colors = ButtonDefaults.buttonColors(
                Color.Red), shape = RoundedCornerShape(15), modifier = Modifier.size(width = 150.dp, height = 50.dp)) {
                Text(text = "Excluir livro")
            }

            Spacer(modifier = Modifier.width(10.dp))

            Button(onClick = {navController.navigate("UpdateScreen")}, colors = ButtonDefaults.buttonColors(
                Color.Red), shape = RoundedCornerShape(15), modifier = Modifier.size(width = 150.dp, height = 50.dp)) {
                Text(text = "Atualizar livro")
            }
        }

    }
}