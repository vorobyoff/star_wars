package com.vorobyoff.starwars.repository

import androidx.room.Database
import androidx.room.RoomDatabase
import com.vorobyoff.starwars.models.Film

@Database(entities = [Film::class], version = 1, exportSchema = false)
abstract class FilmsDatabase : RoomDatabase() {
    abstract fun filmDao(): FilmsDao
}