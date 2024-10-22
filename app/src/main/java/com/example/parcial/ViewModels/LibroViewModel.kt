package com.example.parcial.ViewModels

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.example.parcial.Libro
import com.example.parcial.db.BibliotecaDatabase
import com.example.parcial.repository.LibroRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class LibroViewModel(application: Application) : AndroidViewModel(application) {
    private val libroRepository: LibroRepository

    private val _allLibros = MutableStateFlow<List<Libro>>(emptyList())
    val allLibros: StateFlow<List<Libro>> get() = _allLibros

    init {
        val libroDao = BibliotecaDatabase.getDatabase(application).libroDao()
        libroRepository = LibroRepository(libroDao)

        // Cargar los libros inicialmente
        viewModelScope.launch {
            libroRepository.allLibros.collect { libros ->
                _allLibros.value = libros
            }
        }
    }

    fun insert(libro: Libro) = viewModelScope.launch {
        libroRepository.insert(libro)
    }

    fun delete(libro: Libro) = viewModelScope.launch {
        libroRepository.delete(libro)
    }

    fun update(libro: Libro) = viewModelScope.launch {
        libroRepository.update(libro)
    }
}
