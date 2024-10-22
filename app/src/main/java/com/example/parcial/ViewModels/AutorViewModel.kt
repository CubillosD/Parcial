package com.example.parcial.ViewModels

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.example.parcial.Autor
import com.example.parcial.db.BibliotecaDatabase
import com.example.parcial.repository.AutorRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class AutorViewModel(application: Application) : AndroidViewModel(application) {

    private val autorRepository: AutorRepository

    // Cambia LiveData a StateFlow
    private val _allAutor = MutableStateFlow<List<Autor>>(emptyList())
    val allAutor: StateFlow<List<Autor>> get() = _allAutor

    init {
        val autorDao = BibliotecaDatabase.getDatabase(application).autorDao()
        autorRepository = AutorRepository(autorDao)

        // Cargar los autores inicialmente
        viewModelScope.launch {
            autorRepository.getAllAutores().collect { autores ->
                _allAutor.value = autores
            }
        }
    }

    fun insert(autor: Autor) = viewModelScope.launch {
        autorRepository.insert(autor)
        // No es necesario recargar la lista aquí si estás usando Flow
    }

    fun delete(autor: Autor) = viewModelScope.launch {
        autorRepository.delete(autor)
        // No es necesario recargar la lista aquí si estás usando Flow
    }

    fun update(autor: Autor) = viewModelScope.launch {
        autorRepository.update(autor)
        // No es necesario recargar la lista aquí si estás usando Flow
    }
}
