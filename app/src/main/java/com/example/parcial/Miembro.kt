package com.example.parcial

import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverters
import java.util.Date

@Entity(tableName = "miembros")
@TypeConverters(Converters::class) // Aplica los conversores
data class Miembro(
    @PrimaryKey(autoGenerate = true) val miembro_id: Int = 0,
    val nombre: String,
    val apellido: String,
    val fechaInscripcion: Date
)
