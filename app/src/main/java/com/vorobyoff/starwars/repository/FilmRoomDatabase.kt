package com.vorobyoff.starwars.repository

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.vorobyoff.starwars.models.Film

@Database(entities = [Film::class], version = 1, exportSchema = false)
abstract class FilmRoomDatabase : RoomDatabase() {
    abstract fun filmDao(): FilmDao

    companion object {
        @Volatile
        private var INSTANCE: FilmRoomDatabase? = null
        private const val FILM_DB_NAME = "film_database"

        fun getDatabase(context: Context): FilmRoomDatabase {
            val tempInstance = INSTANCE
            if (tempInstance != null) return tempInstance
            synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    FilmRoomDatabase::class.java,
                    FILM_DB_NAME
                ).build()
                INSTANCE = instance
                return instance
            }
        }
    }
}