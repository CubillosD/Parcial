package com.example.parcial.DAO

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.example.parcial.Libro
import kotlinx.coroutines.flow.Flow

@Dao
interface LibroDao {
    @Query("SELECT * FROM libros")
    fun getAllLibros(): Flow<List<Libro>> // Cambiar a Flow

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(libro: Libro)

    @Delete
    suspend fun delete(libro: Libro)

    @Update
    suspend fun update(libro: Libro)
}
