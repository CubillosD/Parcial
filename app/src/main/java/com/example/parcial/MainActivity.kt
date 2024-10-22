package com.example.parcial

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material3.Surface
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.parcial.Screen.AutorScreen
import com.example.parcial.Screen.LibroScreen
import com.example.parcial.Screen.MiembroScreen
import com.example.parcial.Screen.PrestamoScreen
import com.example.parcial.ui.theme.ParcialTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ParcialTheme { // Cambia YourTheme a ParcialTheme
                Surface {
                    val navController = rememberNavController()
                    NavHost(navController, startDestination = "autores") {
                        composable("autores") { AutorScreen() }
                        composable("libros") { LibroScreen() }
                        composable("miembros") { MiembroScreen() }
                        composable("prestamos") { PrestamoScreen() }
                    }
                }
            }
        }
    }
}
