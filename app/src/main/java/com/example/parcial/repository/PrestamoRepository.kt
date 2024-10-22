package com.example.parcial.repository

import androidx.lifecycle.LiveData
import com.example.parcial.DAO.PrestamoDao
import com.example.parcial.Prestamo


class PrestamoRepository(private val prestamoDao: PrestamoDao) {

    val allPrestamos: LiveData<List<Prestamo>> = prestamoDao.getAllPrestamos()

    suspend fun insert(prestamo: Prestamo) {
        prestamoDao.insert(prestamo)
    }

    suspend fun delete(prestamo: Prestamo) {
        prestamoDao.delete(prestamo)
    }

    suspend fun update(prestamo: Prestamo) {
        prestamoDao.update(prestamo)
    }
}
