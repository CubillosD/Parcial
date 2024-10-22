package com.example.parcial.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.parcial.Autor
import com.example.parcial.DAO.AutorDao
import com.example.parcial.DAO.LibroDao
import com.example.parcial.DAO.MiembroDao
import com.example.parcial.DAO.PrestamoDao
import com.example.parcial.Libro
import com.example.parcial.Miembro
import com.example.parcial.Prestamo
import com.example.parcial.Converters // Asegúrate de importar tu clase de conversores

@Database(entities = [Autor::class, Libro::class, Miembro::class, Prestamo::class], version = 1)
@TypeConverters(Converters::class) // Aplica los conversores aquí
abstract class BibliotecaDatabase : RoomDatabase() {
    abstract fun autorDao(): AutorDao
    abstract fun libroDao(): LibroDao
    abstract fun miembroDao(): MiembroDao
    abstract fun prestamoDao(): PrestamoDao

    companion object {
        @Volatile private var INSTANCE: BibliotecaDatabase? = null

        fun getDatabase(context: Context): BibliotecaDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    BibliotecaDatabase::class.java,
                    "biblioteca_database"
                ).build()
                INSTANCE = instance
                instance
            }
        }
    }
}
