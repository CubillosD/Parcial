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
import com.example.parcial.Prestamo
import com.example.parcial.ViewModels.PrestamoViewModel
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

@Composable
fun PrestamoScreen(prestamoViewModel: PrestamoViewModel = viewModel()) {
    var libroId by remember { mutableStateOf("") }
    var miembroId by remember { mutableStateOf("") }
    var fechaPrestamo by remember { mutableStateOf(SimpleDateFormat("yyyy-MM-dd", Locale.getDefault()).format(Date())) }

    // Usa observeAsState para LiveData
    val prestamos by prestamoViewModel.allPrestamos.observeAsState(initial = emptyList())

    Column(modifier = Modifier.padding(16.dp)) {
        Text("Gestión de Préstamos", style = MaterialTheme.typography.headlineSmall)

        Spacer(modifier = Modifier.height(16.dp))

        TextField(
            value = libroId,
            onValueChange = { libroId = it },
            modifier = Modifier.fillMaxWidth().padding(8.dp),
            label = { Text("ID del Libro") }
        )

        TextField(
            value = miembroId,
            onValueChange = { miembroId = it },
            modifier = Modifier.fillMaxWidth().padding(8.dp),
            label = { Text("ID del Miembro") }
        )

        TextField(
            value = fechaPrestamo,
            onValueChange = { fechaPrestamo = it },
            modifier = Modifier.fillMaxWidth().padding(8.dp),
            label = { Text("Fecha de Préstamo (yyyy-MM-dd)") }
        )

        Button(onClick = {
            if (libroId.isNotEmpty() && miembroId.isNotEmpty() && fechaPrestamo.isNotEmpty()) {
                prestamoViewModel.insert(
                    Prestamo(
                        libro_id = libroId.toInt(),
                        miembro_id = miembroId.toInt(),
                        fechaPrestamo = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault()).parse(fechaPrestamo)!!,
                        fechaDevolucion = null
                    )
                )
                libroId = ""
                miembroId = ""
                fechaPrestamo = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault()).format(Date())
            }
        }) {
            Text("Agregar Préstamo")
        }

        Spacer(modifier = Modifier.height(16.dp))

        LazyColumn {
            items(prestamos) { prestamo ->
                Text("Libro ID: ${prestamo.libro_id}, Miembro ID: ${prestamo.miembro_id}, Fecha de Préstamo: ${prestamo.fechaPrestamo}", modifier = Modifier.padding(8.dp))
            }
        }
    }
}
