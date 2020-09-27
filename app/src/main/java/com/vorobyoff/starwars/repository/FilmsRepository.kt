package com.vorobyoff.starwars.repository

import androidx.lifecycle.LiveData
import com.vorobyoff.starwars.models.Film

interface FilmsRepository {
    fun saveFilm(film: Film)
    fun getAllFilms(): LiveData<List<Film>>
    fun deleteAllFilms()
}