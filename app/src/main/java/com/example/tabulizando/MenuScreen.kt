package com.example.tabulizando

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController

@Preview
@Composable
fun MenuScreen(navController: NavController){
    Column(modifier = Modifier.fillMaxSize(),verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally) {

        Text(text = "Menu black cover",
            fontSize = 20.sp)

        Spacer(modifier = Modifier.height(10.dp))
        Row {
            Button(onClick = {navController.navigate("RegisterScreen")}) {
                Text(text = "Cadastrar livro")
            }
            Button(onClick = {navController.navigate("ListScreen")}) {
                Text(text = "Listar livros")
            }
        }
        Row {
            Button(onClick = {}) {
                Text(text = "Excluir livro")
            }
            Button(onClick = {}) {
                Text(text = "Atualizar livro")
            }
        }

    }
}