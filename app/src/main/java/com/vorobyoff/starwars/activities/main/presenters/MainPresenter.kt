package com.vorobyoff.starwars.activities.main.presenters

import androidx.lifecycle.LiveData
import com.vorobyoff.starwars.App
import com.vorobyoff.starwars.api.NetworkService
import com.vorobyoff.starwars.models.Film
import com.vorobyoff.starwars.models.FilmsResponse
import com.vorobyoff.starwars.repository.FilmRepository
import com.vorobyoff.starwars.repository.FilmRoomDatabase
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import moxy.InjectViewState
import moxy.MvpPresenter
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

@InjectViewState
class MainPresenter : MvpPresenter<MainView>() {
    private val films = mutableListOf<Film>()
    private val repository: FilmRepository
    val favoriteFilms: LiveData<List<Film>>

    init {
        val dao = FilmRoomDatabase.getDatabase(App().applicationContext).filmDao()
        repository = FilmRepository(dao)
        favoriteFilms = repository.data
    }

    fun getFilms() {
        GlobalScope.launch {
            NetworkService.getSWApi()?.getFilms()?.enqueue(object : Callback<FilmsResponse> {
                override fun onResponse(
                    call: Call<FilmsResponse>,
                    response: Response<FilmsResponse>
                ) {
                    if (response.isSuccessful) {
                        val filmsResponse = response.body()
                        filmsResponse?.let { res ->
                            res.results.forEach { films += it }
                            films.sort()
                            viewState.setFilms(films)
                        }
                    }
                }

                override fun onFailure(call: Call<FilmsResponse>, t: Throwable) {}
            })
        }
    }

    fun saveFilm(film: Film) = GlobalScope.launch { repository.insert(film) }
}