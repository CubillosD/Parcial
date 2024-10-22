package com.example.parcial.DAO

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.example.parcial.Prestamo

@Dao
interface PrestamoDao {
    @Query("SELECT * FROM prestamos")
    fun getAllPrestamos(): LiveData<List<Prestamo>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(prestamo: Prestamo)

    @Delete
    suspend fun delete(prestamo: Prestamo)

    @Update
    suspend fun update(prestamo: Prestamo)
}
