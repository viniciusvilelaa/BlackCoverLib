package com.example.tabulizando

import android.content.Context
import android.content.SharedPreferences
import android.view.Menu
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity.MODE_PRIVATE
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController


@Composable
fun LoginScreen(context: Context, navController: NavController){



    val sharedPreferences = context.getSharedPreferences("login_prefs", Context.MODE_PRIVATE)




    var user by remember {
        mutableStateOf("")
    }

    var password by remember {
        mutableStateOf("")
    }
    LaunchedEffect(Unit) {
        user = sharedPreferences.getString("user", "") ?: ""
        password = sharedPreferences.getString("password", "") ?: ""
    }


    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally

    ){
        Text(
            text = "Black Cover",
            fontSize = 28.sp,
            fontWeight = FontWeight.Bold
        )

        Spacer(modifier = Modifier.height(5.dp))

        Text(
            text = "Seja muito bem vindo!!"
        )

        Spacer(modifier = Modifier.height(10.dp))

        OutlinedTextField(value = user, onValueChange = {user = it}, label = { Text(text = "User") })

        Spacer(modifier = Modifier.height(10.dp))

        OutlinedTextField(value = password, onValueChange = {password = it}, label = { Text(text = "Password") })

        Spacer(modifier = Modifier.height(10.dp))


        Button(onClick = { login(context, sharedPreferences, user, password, navController) }, colors = ButtonDefaults.buttonColors(Color.Red)){
            Text(text = "Login")
        }

        Button(onClick = {}, colors = ButtonDefaults.buttonColors(Color.DarkGray)) {
            Text(text = "Cadastrar")
        }


        Spacer(modifier = Modifier.height(10.dp))

        TextButton(onClick = {}) {
            Text(text = "Redefinir senha", color = Color.Red)
        }




    }




}



fun login(context: Context,sharedPreferences: SharedPreferences, user: String, password: String, navController: NavController){
    if (user == "Admin" && password == "Admin"){
        savePrefs(sharedPreferences, user,password)
        Toast.makeText(context, "Login efetuado!", Toast.LENGTH_LONG).show()
        navController.navigate("MenuScreen")

    } else{
        Toast.makeText(context,"Usuário ou senha incorretos", Toast.LENGTH_LONG).show()
    }
}


fun savePrefs(sharedPreferences: SharedPreferences,user: String, password: String){
    val editor = sharedPreferences.edit()
    editor.putString("user", user)
    editor.putString("password", password)
    editor.apply()
}
