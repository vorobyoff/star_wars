package com.vorobyoff.starwars.presenters

import com.vorobyoff.starwars.api.NetworkService
import com.vorobyoff.starwars.models.Film
import com.vorobyoff.starwars.models.FilmsResponse
import com.vorobyoff.starwars.repository.FilmRepository
import com.vorobyoff.starwars.views.MainView
import kotlinx.coroutines.launch
import moxy.InjectViewState
import moxy.MvpPresenter
import moxy.presenterScope
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

@InjectViewState
class MainPresenter(private val repository: FilmRepository) : MvpPresenter<MainView>() {
    val favoriteFilms = repository.data

    fun getFilms() {
        presenterScope.launch {
            NetworkService.getSWApi()?.getFilms()?.enqueue(object : Callback<FilmsResponse> {
                override fun onResponse(call: Call<FilmsResponse>, resp: Response<FilmsResponse>) {
                    if (resp.isSuccessful) resp.body()
                        ?.let { viewState.set(it.results.sorted()) }
                }

                override fun onFailure(call: Call<FilmsResponse>, t: Throwable) {}
            })
        }
    }

    fun insert(film: Film) = presenterScope.launch { repository.insert(film) }

    fun delete(film: Film) = presenterScope.launch { repository.delete(film) }

    fun delete() = presenterScope.launch { repository.delete() }
}