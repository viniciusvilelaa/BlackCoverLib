package com.example.tabulizando

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.node.ModifierNodeElement
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.bumptech.glide.integration.compose.ExperimentalGlideComposeApi
import com.bumptech.glide.integration.compose.GlideImage

//Função de config do componente Card que abre ao clicar no livro na tela de listagem
@OptIn(ExperimentalGlideComposeApi::class, ExperimentalMaterial3Api::class)
@Composable
fun CardDetail(book: Book){
    Card(modifier = Modifier.width(300.dp)
        .height(430.dp),
        elevation = CardDefaults.cardElevation(10.dp),
        colors = CardDefaults.cardColors(Color.White)) {

        Column(modifier = Modifier.fillMaxSize(), horizontalAlignment = Alignment.CenterHorizontally) {
            GlideImage(model = book.imgUrl, contentDescription = "Foto livro", modifier = Modifier.width(300.dp)
                .height(175.dp), )

            Text(text = book.title, fontSize = 18.sp, fontWeight = FontWeight.Bold,
                modifier = Modifier.padding(10.dp))

            Text(text = book.author,fontSize = 13.sp, fontStyle = FontStyle.Italic,
                modifier = Modifier.padding(6.dp))

            Text(text = book.publisher, fontSize = 13.sp, fontStyle = FontStyle.Italic,
                modifier = Modifier.padding(6.dp))

            Text(text = book.isbn.toString(), fontSize = 13.sp, fontStyle = FontStyle.Italic,
                modifier = Modifier.padding(6.dp))

            Text(text = book.description, fontSize = 13.sp, fontStyle = FontStyle.Italic,
                modifier = Modifier.padding(6.dp))

        }
        
    }
}