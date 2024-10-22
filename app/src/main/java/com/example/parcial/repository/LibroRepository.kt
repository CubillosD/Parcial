package com.example.parcial.repository

import com.example.parcial.DAO.LibroDao
import com.example.parcial.Libro
import kotlinx.coroutines.flow.Flow

class LibroRepository(private val libroDao: LibroDao) {
    val allLibros: Flow<List<Libro>> = libroDao.getAllLibros() // Cambiar a Flow

    suspend fun insert(libro: Libro) {
        libroDao.insert(libro)
    }

    suspend fun delete(libro: Libro) {
        libroDao.delete(libro)
    }

    suspend fun update(libro: Libro) {
        libroDao.update(libro)
    }
}
