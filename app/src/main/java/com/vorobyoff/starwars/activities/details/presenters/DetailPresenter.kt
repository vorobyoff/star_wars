package com.vorobyoff.starwars.activities.details.presenters

import com.vorobyoff.starwars.api.NetworkService
import com.vorobyoff.starwars.models.Film
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import moxy.InjectViewState
import moxy.MvpPresenter
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

@InjectViewState
class DetailPresenter : MvpPresenter<DetailView>() {
    fun getData(url: String) {
        GlobalScope.launch {
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