package com.vorobyoff.starwars.activities.main.presenters

import com.vorobyoff.starwars.models.Film
import moxy.MvpView
import moxy.viewstate.strategy.AddToEndSingleStrategy
import moxy.viewstate.strategy.StateStrategyType

@StateStrategyType(AddToEndSingleStrategy::class)
interface MainView : MvpView {
    fun setFilms(films: List<Film>)
}