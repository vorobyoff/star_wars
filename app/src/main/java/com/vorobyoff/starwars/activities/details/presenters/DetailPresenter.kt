package com.vorobyoff.starwars.activities.details.presenters

import com.vorobyoff.starwars.activities.details.DetailActivity
import com.vorobyoff.starwars.api.NetworkService
import com.vorobyoff.starwars.models.Film
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class DetailPresenter(private val activity: DetailActivity) : Presenter {
    override fun show(url: String) {
        val validUrl = url.let {
            val lastIndex = it.lastIndexOf("films/")
            it.substring(lastIndex)
        }

        NetworkService.getSWApi()?.getFilm(validUrl)?.enqueue(object : Callback<Film> {
            override fun onResponse(call: Call<Film>, response: Response<Film>) {
                if (response.isSuccessful) {
                    val film = response.body()
                    if (film != null) activity.show(film)
                }
            }

            override fun onFailure(call: Call<Film>, t: Throwable) {}
        })
    }
}