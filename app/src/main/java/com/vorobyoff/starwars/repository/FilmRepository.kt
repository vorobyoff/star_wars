package com.vorobyoff.starwars.repository

import androidx.lifecycle.LiveData
import com.vorobyoff.starwars.models.Film

class FilmRepository(private val dao: FilmDao) {
    val data: LiveData<List<Film>> = dao.get()

    suspend fun insert(vararg films: Film) = dao.insert(*films)

    suspend fun delete() = dao.delete()

    suspend fun delete(film: Film) = dao.delete(film)
}