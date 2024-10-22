package com.example.parcial

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey
import java.util.Date

@Entity(
    tableName = "prestamos",
    foreignKeys = [
        ForeignKey(entity = Libro::class,
            parentColumns = ["libro_id"],
            childColumns = ["libro_id"],
            onDelete = ForeignKey.CASCADE),
        ForeignKey(entity = Miembro::class,
            parentColumns = ["miembro_id"],
            childColumns = ["miembro_id"],
            onDelete = ForeignKey.CASCADE)
    ]
)
data class Prestamo(
    @PrimaryKey(autoGenerate = true) val prestamo_id: Int = 0,
    val libro_id: Int,
    val miembro_id: Int,
    val fechaPrestamo: Date,
    val fechaDevolucion: Date?
)
