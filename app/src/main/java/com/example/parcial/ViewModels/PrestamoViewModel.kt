package com.example.parcial.ViewModels

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.example.parcial.Prestamo
import com.example.parcial.db.BibliotecaDatabase
import com.example.parcial.repository.PrestamoRepository
import kotlinx.coroutines.launch

class PrestamoViewModel(application: Application) : AndroidViewModel(application) {

    private val prestamoRepository: PrestamoRepository
    val allPrestamos: LiveData<List<Prestamo>> // Esta propiedad ya está correcta

    init {
        // Obtiene la instancia del DAO a través de la base de datos
        val prestamoDao = BibliotecaDatabase.getDatabase(application).prestamoDao()
        prestamoRepository = PrestamoRepository(prestamoDao)
        allPrestamos = prestamoRepository.allPrestamos
    }

    fun insert(prestamo: Prestamo) = viewModelScope.launch {
        prestamoRepository.insert(prestamo)
    }

    fun delete(prestamo: Prestamo) = viewModelScope.launch {
        prestamoRepository.delete(prestamo)
    }

    fun update(prestamo: Prestamo) = viewModelScope.launch {
        prestamoRepository.update(prestamo)
    }
}
