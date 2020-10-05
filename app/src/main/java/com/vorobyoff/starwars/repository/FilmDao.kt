package com.vorobyoff.starwars.repository

import androidx.lifecycle.LiveData
import androidx.room.*
import com.vorobyoff.starwars.models.Film

@Dao
interface FilmDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(film: Film)

    @Query("SELECT * FROM films_table ORDER BY title ASC")
    fun get(): LiveData<List<Film>>

    @Query("DELETE FROM films_table")
    suspend fun delete()

    @Delete
    suspend fun delete(film: Film)
}