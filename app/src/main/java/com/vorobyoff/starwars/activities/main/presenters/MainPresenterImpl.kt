package com.vorobyoff.starwars.activities.main.presenters

import com.vorobyoff.starwars.api.NetworkService
import com.vorobyoff.starwars.models.Film
import com.vorobyoff.starwars.models.FilmsResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.lang.ref.WeakReference

class MainPresenterImpl : MainPresenter {
    private var viewState: WeakReference<MainView>? = null

    override fun getFilms() {
        val films = mutableListOf<Film>()

        NetworkService.getSWApi()?.getFilms()?.enqueue(object : Callback<FilmsResponse> {
            override fun onResponse(call: Call<FilmsResponse>, response: Response<FilmsResponse>) {
                if (response.isSuccessful) {
                    val filmsResponse = response.body()
                    filmsResponse?.let { r -> r.results.forEach { films += it } }
                    films.sort()
                    viewState?.get()?.setFilms(films)
                }
            }

            override fun onFailure(call: Call<FilmsResponse>, t: Throwable) {}
        })
    }

    fun attachView(view: MainView) {
        viewState = WeakReference(view)
    }
}