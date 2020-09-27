package com.vorobyoff.starwars.repository

import androidx.lifecycle.LiveData
import com.vorobyoff.starwars.models.Film

class FilmRepositoryImpl : FilmsRepository {
    override fun saveFilm(film: Film) {
        TODO("Not yet implemented")
    }

    override fun getAllFilms(): LiveData<List<Film>> {
        TODO("Not yet implemented")
    }

    override fun deleteAllFilms() {
        TODO("Not yet implemented")
    }
}