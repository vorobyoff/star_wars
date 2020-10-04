package com.vorobyoff.starwars.activities.details.presenters

import com.vorobyoff.starwars.api.NetworkService
import com.vorobyoff.starwars.models.Film
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.launch
import moxy.InjectViewState
import moxy.MvpPresenter
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import kotlin.coroutines.CoroutineContext

@InjectViewState
class DetailPresenter : MvpPresenter<DetailView>(), CoroutineScope {
    private val job = SupervisorJob()

    fun getData(url: String) {
        launch {
            val validUrl = urlValidator(url)

            NetworkService.getSWApi()?.getFilm(validUrl)?.enqueue(object : Callback<Film> {
                override fun onResponse(call: Call<Film>, response: Response<Film>) {
                    if (response.isSuccessful) response.body()?.let { viewState.show(it) }
                }

                override fun onFailure(call: Call<Film>, t: Throwable) {}
            })
        }
    }

    override val coroutineContext: CoroutineContext
        get() = Dispatchers.Main + job

    private fun urlValidator(url: String) = url.let {
        val lastIndex = it.lastIndexOf("films/")
        it.substring(lastIndex)
    }
}