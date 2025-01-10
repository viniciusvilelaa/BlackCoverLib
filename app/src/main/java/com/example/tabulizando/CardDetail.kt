package com.example.tabulizando

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
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

@OptIn(ExperimentalGlideComposeApi::class)
@Preview
@Composable
fun CardDetail(){
    Card(modifier = Modifier.width(200.dp)
        .height(270.dp),
        elevation = CardDefaults.cardElevation(10.dp),
        colors = CardDefaults.cardColors(Color.White)) {

        Column(modifier = Modifier.fillMaxSize()) {
            GlideImage(model = "https://m.media-amazon.com/images/I/41uDEyaWOiL._AC_UF1000,1000_QL80_.jpg", contentDescription = "Foto livro", modifier = Modifier.width(200.dp)
                .height(120.dp), )

            Text(text = "Colecionador", fontSize = 18.sp, fontWeight = FontWeight.Bold, modifier = Modifier.padding(10.dp))
            Text(text = "Rafael Montes", modifier = Modifier.padding(6.dp), fontStyle = FontStyle.Italic)
            Text(text = "123", modifier = Modifier.padding(6.dp), fontStyle = FontStyle.Italic)
            Text(text = "Desc", modifier = Modifier.padding(6.dp), fontStyle = FontStyle.Italic)

        }
        
    }
}