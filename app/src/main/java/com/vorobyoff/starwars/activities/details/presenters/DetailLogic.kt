package com.vorobyoff.starwars.activities.details.presenters

import com.vorobyoff.starwars.models.Film

interface DetailPresenter {
    fun getData(url: String)
}

interface DetailView {
    fun show(film: Film)
}