package com.vorobyoff.starwars.repository

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.vorobyoff.starwars.models.Film
import io.reactivex.rxjava3.core.Flowable

@Dao
interface FilmsDao {
    @Insert
    fun insertFilm(films: Film)

    @Delete
    fun deleteFilms(vararg films: Film)

    @Query("SELECT * FROM films_table ORDER BY title ASC")
    fun getAllFilms(): Flowable<List<Film>>
}