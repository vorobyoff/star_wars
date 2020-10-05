package com.vorobyoff.starwars.presenters

import com.vorobyoff.starwars.api.NetworkService
import com.vorobyoff.starwars.models.Film
import com.vorobyoff.starwars.views.DetailView
import kotlinx.coroutines.launch
import moxy.InjectViewState
import moxy.MvpPresenter
import moxy.presenterScope
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

@InjectViewState
class DetailPresenter : MvpPresenter<DetailView>() {
    fun getData(url: String) {
        presenterScope.launch {
            val validUrl = urlValidator(url)

            NetworkService.getSWApi()?.getFilm(validUrl)?.enqueue(object : Callback<Film> {
                override fun onResponse(call: Call<Film>, response: Response<Film>) {
                    if (response.isSuccessful) response.body()?.let { viewState.show(it) }
                }

                override fun onFailure(call: Call<Film>, t: Throwable) {}
            })
        }
    }

    private fun urlValidator(url: String) = url.let {
        val lastIndex = it.lastIndexOf("films/")
        it.substring(lastIndex)
    }
}