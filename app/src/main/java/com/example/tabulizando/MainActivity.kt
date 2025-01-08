package com.example.tabulizando

import android.content.Context
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.tabulizando.ui.theme.TabulizandoTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            TabulizandoTheme {
                val navController = rememberNavController()
                NavHost(navController = navController, startDestination = "LoginScreen"){
                    composable("LoginScreen"){
                        LoginScreen(this@MainActivity, navController)
                    }
                    composable("MenuScreen"){
                        MenuScreen(navController)
                    }
                    composable("RegisterScreen"){
                        RegisterScreen(navController, this@MainActivity)
                    }
                }
            }
        }
    }
}


