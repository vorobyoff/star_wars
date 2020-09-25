package com.vorobyoff.starwars.activities.details.presenters

import com.vorobyoff.starwars.api.NetworkService
import com.vorobyoff.starwars.models.Film
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.lang.ref.WeakReference

class DetailPresenterImpl : DetailPresenter {
    private var viewState: WeakReference<DetailView>? = null

    override fun getFilm(url: String) {
        val validUrl = urlValidator(url)

        NetworkService.getSWApi()?.getFilm(validUrl)?.enqueue(object : Callback<Film> {
            override fun onResponse(call: Call<Film>, response: Response<Film>) {
                if (response.isSuccessful) {
                    val film = response.body()
                    if (film != null) viewState?.get()?.showFilm(film)
                }
            }

            override fun onFailure(call: Call<Film>, t: Throwable) {}
        })
    }

    private fun urlValidator(url: String) = url.let {
        val lastIndex = it.lastIndexOf("films/")
        it.substring(lastIndex)
    }

    fun attachView(view: DetailView) {
        viewState = WeakReference(view)
    }
}