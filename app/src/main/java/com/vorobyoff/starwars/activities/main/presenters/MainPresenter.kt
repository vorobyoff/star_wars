package com.vorobyoff.starwars.activities.main.presenters

import com.vorobyoff.starwars.activities.main.MainActivity
import com.vorobyoff.starwars.api.NetworkService
import com.vorobyoff.starwars.models.Film
import com.vorobyoff.starwars.models.FilmsResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainPresenter(private val activity: MainActivity) : Presenter {
    override fun update() {
        val films = mutableListOf<Film>()

        NetworkService.getSWApi()?.getFilms()?.enqueue(object : Callback<FilmsResponse> {
            override fun onResponse(call: Call<FilmsResponse>, response: Response<FilmsResponse>) {
                if (response.isSuccessful) {
                    val filmsResponse = response.body()
                    filmsResponse?.let { r -> r.results.forEach { films += it } }
                    films.sort()
                    activity.update(films)
                }
            }

            override fun onFailure(call: Call<FilmsResponse>, t: Throwable) {}
        })
    }
}