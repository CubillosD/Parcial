package com.example.parcial.repository

import androidx.lifecycle.LiveData
import com.example.parcial.DAO.MiembroDao
import com.example.parcial.Miembro

class MiembroRepository(private val miembroDao: MiembroDao) {

    val allMiembros: LiveData<List<Miembro>> = miembroDao.getAllMiembros()

    suspend fun insert(miembro: Miembro) {
        miembroDao.insert(miembro)
    }

    suspend fun delete(miembro: Miembro) {
        miembroDao.delete(miembro)
    }

    suspend fun update(miembro: Miembro) {
        miembroDao.update(miembro)
    }
}
