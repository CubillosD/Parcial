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
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.parcial.Miembro
import com.example.parcial.ViewModels.MiembroViewModel
import java.util.Date

@Composable
fun MiembroScreen(miembroViewModel: MiembroViewModel = viewModel()) {
    var nombre by remember { mutableStateOf("") }
    var apellido by remember { mutableStateOf("") } // Asegúrate de que este campo esté presente
    var fechaInscripcion by remember { mutableStateOf(Date()) } // Cambiar a Date
    val miembros by miembroViewModel.allMiembros.observeAsState(initial = emptyList())

    Column(modifier = Modifier.padding(16.dp)) {
        Text("Gestión de Miembros", style = MaterialTheme.typography.headlineSmall)

        Spacer(modifier = Modifier.height(16.dp))

        TextField(
            value = nombre,
            onValueChange = { nombre = it },
            modifier = Modifier.fillMaxWidth().padding(8.dp),
            label = { Text("Nombre") }
        )

        TextField(
            value = apellido,
            onValueChange = { apellido = it },
            modifier = Modifier.fillMaxWidth().padding(8.dp),
            label = { Text("Apellido") }
        )

        Button(onClick = {
            if (nombre.isNotEmpty() && apellido.isNotEmpty()) {
                // Aquí asume que tienes un conversor o una forma de establecer la fecha
                miembroViewModel.insert(Miembro(nombre = nombre, apellido = apellido, fechaInscripcion = fechaInscripcion))
                nombre = ""
                apellido = ""
            }
        }) {
            Text("Agregar Miembro")
        }

        Spacer(modifier = Modifier.height(16.dp))

        LazyColumn {
            items(miembros) { miembro ->
                Text("${miembro.nombre} ${miembro.apellido} (ID: ${miembro.miembro_id})", modifier = Modifier.padding(8.dp))
            }
        }
    }
}
