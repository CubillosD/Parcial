package com.example.parcial.Screen

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.parcial.Libro
import com.example.parcial.ViewModels.LibroViewModel

@Composable
fun LibroScreen(libroViewModel: LibroViewModel = viewModel()) {
    var titulo by remember { mutableStateOf("") }
    var autorId by remember { mutableStateOf("") }
    var genero by remember { mutableStateOf("") } // Añadir campo de género
    var errorMessage by remember { mutableStateOf("") }

    // Asegúrate de que allLibros sea un StateFlow
    val libros by libroViewModel.allLibros.collectAsState(initial = emptyList())

    Column(modifier = Modifier.padding(16.dp)) {
        Text("Gestión de Libros", style = MaterialTheme.typography.headlineSmall)

        Spacer(modifier = Modifier.height(16.dp))

        // Campo de texto para el título del libro
        TextField(
            value = titulo,
            onValueChange = { titulo = it },
            modifier = Modifier.fillMaxWidth().padding(8.dp),
            label = { Text("Título") }
        )

        // Campo de texto para el género del libro
        TextField(
            value = genero,
            onValueChange = { genero = it },
            modifier = Modifier.fillMaxWidth().padding(8.dp),
            label = { Text("Género") }
        )

        // Campo de texto para el ID del autor
        TextField(
            value = autorId,
            onValueChange = {
                autorId = it
                errorMessage = "" // Limpiar mensaje de error al cambiar el ID
            },
            modifier = Modifier.fillMaxWidth().padding(8.dp),
            label = { Text("ID del Autor") }
        )

        // Botón para agregar un nuevo libro
        Button(onClick = {
            if (titulo.isNotEmpty() && genero.isNotEmpty() && autorId.isNotEmpty()) {
                try {
                    val id = autorId.toInt() // Convertir el ID a entero
                    libroViewModel.insert(Libro(titulo = titulo, genero = genero, autor_id = id)) // Usar autor_id en lugar de autorId
                    titulo = ""
                    genero = ""
                    autorId = ""
                } catch (e: NumberFormatException) {
                    errorMessage = "Por favor, ingresa un ID de autor válido." // Mostrar mensaje de error
                }
            } else {
                errorMessage = "Por favor, completa todos los campos." // Mensaje si los campos están vacíos
            }
        }) {
            Text("Agregar Libro")
        }

        // Mostrar mensaje de error si hay alguno
        if (errorMessage.isNotEmpty()) {
            Text(text = errorMessage, color = MaterialTheme.colorScheme.error)
            Spacer(modifier = Modifier.height(8.dp))
        }

        Spacer(modifier = Modifier.height(16.dp))

        // Mostrar la lista de libros
        LazyColumn {
            items(libros) { libro ->
                Text("${libro.titulo} - ${libro.genero} (Autor ID: ${libro.autor_id})", modifier = Modifier.padding(8.dp))
            }
        }
    }
}
