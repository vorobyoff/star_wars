package com.vorobyoff.starwars.activities.details.presenters

import com.vorobyoff.starwars.models.Film
import moxy.MvpView
import moxy.viewstate.strategy.AddToEndSingleStrategy
import moxy.viewstate.strategy.StateStrategyType

@StateStrategyType(AddToEndSingleStrategy::class)
interface DetailView : MvpView {
    fun show(film: Film)
}