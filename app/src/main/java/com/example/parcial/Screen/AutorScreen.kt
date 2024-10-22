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
import com.example.parcial.Autor
import com.example.parcial.ViewModels.AutorViewModel

@Composable
fun AutorScreen(autorViewModel: AutorViewModel = viewModel()) {
    var nombre by remember { mutableStateOf("") }
    var apellido by remember { mutableStateOf("") }
    var nacionalidad by remember { mutableStateOf("") } // Agregar estado para nacionalidad

    // Reemplaza collectAsState() por el flujo adecuado (StateFlow o LiveData)
    val autores by autorViewModel.allAutor.collectAsState()

    Column(modifier = Modifier.padding(16.dp)) {
        Text("Gestión de Autores", style = MaterialTheme.typography.headlineSmall)

        Spacer(modifier = Modifier.height(16.dp))

        // Campo de texto para el nombre
        TextField(
            value = nombre,
            onValueChange = { nombre = it },
            modifier = Modifier.fillMaxWidth().padding(8.dp),
            label = { Text("Nombre") }
        )

        // Campo de texto para el apellido
        TextField(
            value = apellido,
            onValueChange = { apellido = it },
            modifier = Modifier.fillMaxWidth().padding(8.dp),
            label = { Text("Apellido") }
        )

        // Campo de texto para la nacionalidad
        TextField(
            value = nacionalidad,
            onValueChange = { nacionalidad = it },
            modifier = Modifier.fillMaxWidth().padding(8.dp),
            label = { Text("Nacionalidad") } // Campo para nacionalidad
        )

        // Botón para agregar un nuevo autor
        Button(onClick = {
            if (nombre.isNotEmpty() && apellido.isNotEmpty() && nacionalidad.isNotEmpty()) {
                autorViewModel.insert(Autor(nombre = nombre, apellido = apellido, nacionalidad = nacionalidad)) // Proporcionar nacionalidad
                nombre = "" // Limpiar el campo después de agregar
                apellido = "" // Limpiar el campo después de agregar
                nacionalidad = "" // Limpiar el campo de nacionalidad
            }
        }) {
            Text("Agregar Autor")
        }

        Spacer(modifier = Modifier.height(16.dp))

        // Lista de autores
        LazyColumn {
            items(autores) { autor ->
                Text("${autor.nombre} ${autor.apellido} (${autor.nacionalidad})", modifier = Modifier.padding(8.dp)) // Mostrar nacionalidad
            }
        }
    }
}
