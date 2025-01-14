package com.example.tabulizando


import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
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
                val dbHelper = DataBaseHelper(this)
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
                    composable("ListScreen"){
                        ListScreen(dbHelper, navController)
                    }
                    composable("DeleteScreen"){
                        DeleteScreen(dbHelper, navController, this@MainActivity)
                    }
                    composable("UpdateScreen"){
                        UpdateScreen(navController)
                    }
                }
            }
        }
    }
}


