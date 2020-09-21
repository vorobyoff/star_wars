package com.vorobyoff.starwars.activities.main

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.vorobyoff.starwars.api.NetworkService
import com.vorobyoff.starwars.databinding.ActivityMainBinding
import com.vorobyoff.starwars.models.Film
import com.vorobyoff.starwars.models.FilmsResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {
    private lateinit var mainBinding: ActivityMainBinding
    private lateinit var filmAdapter: FilmAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mainBinding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(mainBinding.root)

        filmAdapter = FilmAdapter(getAllFilms(this))
        mainBinding.filmsRecyclerView.apply {
            layoutManager = LinearLayoutManager(this@MainActivity)
            hasFixedSize()
            adapter = filmAdapter
        }
    }
}

private fun getAllFilms(context: Context): MutableList<Film> {
    val films = mutableListOf<Film>()

    NetworkService.getSWApi()?.getFilms()?.enqueue(object : Callback<FilmsResponse> {
        override fun onResponse(call: Call<FilmsResponse>, response: Response<FilmsResponse>) {
            if (response.isSuccessful) {
                val filmsResponse = response.body()
                filmsResponse?.results?.forEach { films.add(it) }
            }
        }

        override fun onFailure(call: Call<FilmsResponse>, t: Throwable) =
            Toast.makeText(context, "", Toast.LENGTH_SHORT).show()
    })

    return films
}