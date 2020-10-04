package com.vorobyoff.starwars.activities.main.presenters

import android.content.Context
import androidx.lifecycle.LiveData
import com.vorobyoff.starwars.api.NetworkService
import com.vorobyoff.starwars.models.Film
import com.vorobyoff.starwars.models.FilmsResponse
import com.vorobyoff.starwars.repository.FilmRepository
import com.vorobyoff.starwars.repository.FilmRoomDatabase
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
class MainPresenter(applicationContext: Context) : MvpPresenter<MainView>(), CoroutineScope {
    val favoriteFilms: LiveData<List<Film>>
    private val repository: FilmRepository
    private val job = SupervisorJob()

    init {
        val dao = FilmRoomDatabase.getDatabase(applicationContext).filmDao()
        repository = FilmRepository(dao)
        favoriteFilms = repository.data
    }

    fun getFilms() {
        launch {
            NetworkService.getSWApi()?.getFilms()?.enqueue(object : Callback<FilmsResponse> {
                override fun onResponse(call: Call<FilmsResponse>, resp: Response<FilmsResponse>) {
                    if (resp.isSuccessful) resp.body()
                        ?.let { viewState.setFilms(it.results.sorted()) }
                }

                override fun onFailure(call: Call<FilmsResponse>, t: Throwable) {}
            })
        }
    }

    override val coroutineContext: CoroutineContext
        get() = Dispatchers.IO + job

    fun insert(film: Film) = launch { repository.insert(film) }

    fun delete(film: Film) = launch { repository.delete(film) }

    fun delete() = launch { repository.delete() }
}