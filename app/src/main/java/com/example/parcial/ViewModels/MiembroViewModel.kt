package com.example.parcial.ViewModels

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.example.parcial.Miembro
import com.example.parcial.db.BibliotecaDatabase
import com.example.parcial.repository.MiembroRepository
import kotlinx.coroutines.launch


class MiembroViewModel(application: Application) : AndroidViewModel(application) {

    private val miembroRepository: MiembroRepository
    val allMiembros: LiveData<List<Miembro>>

    init {
        val miembroDao = BibliotecaDatabase.getDatabase(application).miembroDao()
        miembroRepository = MiembroRepository(miembroDao)
        allMiembros = miembroRepository.allMiembros
    }

    fun insert(miembro: Miembro) = viewModelScope.launch {
        miembroRepository.insert(miembro)
    }

    fun delete(miembro: Miembro) = viewModelScope.launch {
        miembroRepository.delete(miembro)
    }

    fun update(miembro: Miembro) = viewModelScope.launch {
        miembroRepository.update(miembro)
    }
}
