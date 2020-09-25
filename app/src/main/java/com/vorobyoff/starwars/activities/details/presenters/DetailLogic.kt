package com.vorobyoff.starwars.activities.details.presenters

import com.vorobyoff.starwars.models.Film

interface DetailPresenter {
    fun getFilm(url: String)
}

interface DetailView {
    fun showFilm(film: Film)
}