package com.example.parcial.repository

import com.example.parcial.Autor
import com.example.parcial.DAO.AutorDao
import kotlinx.coroutines.flow.Flow

class AutorRepository(private val autorDao: AutorDao) {


    fun getAllAutores(): Flow<List<Autor>> {
        return autorDao.getAllAutor()
    }

    suspend fun insert(autor: Autor) {
        autorDao.insert(autor)
    }

    suspend fun delete(autor: Autor) {
        autorDao.delete(autor)
    }

    suspend fun update(autor: Autor) {
        autorDao.update(autor)
    }
}
