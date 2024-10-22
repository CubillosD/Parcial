package com.example.parcial.DAO

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.example.parcial.Miembro // Asegúrate de que la importación sea correcta

@Dao
interface MiembroDao {
    @Query("SELECT * FROM miembros")
    fun getAllMiembros(): LiveData<List<Miembro>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(miembro: Miembro)

    @Delete
    suspend fun delete(miembro: Miembro)

    @Update
    suspend fun update(miembro: Miembro)
}
