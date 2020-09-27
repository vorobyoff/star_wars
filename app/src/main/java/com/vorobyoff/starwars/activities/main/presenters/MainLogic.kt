package com.vorobyoff.starwars.activities.main.presenters

import com.vorobyoff.starwars.models.Film

interface MainPresenter {
    fun getFilms()
    fun saveFilm(film: Film)
}

interface MainView {
    fun setFilms(films: List<Film>)
}