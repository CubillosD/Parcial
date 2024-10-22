package com.example.parcial.DAO

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.example.parcial.Autor
import kotlinx.coroutines.flow.Flow

@Dao
interface AutorDao {
    @Query("SELECT * FROM autores")
    fun getAllAutor(): Flow<List<Autor>> // Cambia LiveData a Flow

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(autor: Autor)

    @Delete
    suspend fun delete(autor: Autor)

    @Update
    suspend fun update(autor: Autor)
}
