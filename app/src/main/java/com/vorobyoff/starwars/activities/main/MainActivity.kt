package com.vorobyoff.starwars.activities.main

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.vorobyoff.starwars.activities.details.DetailActivity
import com.vorobyoff.starwars.activities.main.adapter.OnClickListener
import com.vorobyoff.starwars.activities.main.adapter.FilmAdapter
import com.vorobyoff.starwars.api.NetworkService
import com.vorobyoff.starwars.databinding.ActivityMainBinding
import com.vorobyoff.starwars.databinding.MainActionBarBinding
import com.vorobyoff.starwars.models.Film
import com.vorobyoff.starwars.models.FilmsResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity(), OnClickListener {
    private lateinit var mainBinding: ActivityMainBinding
    private lateinit var filmAdapter: FilmAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mainBinding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(mainBinding.root)
        setSupportActionBar(MainActionBarBinding.inflate(layoutInflater).root)

        filmAdapter = FilmAdapter(this)
        mainBinding.filmsRecyclerView.apply {
            layoutManager = LinearLayoutManager(this@MainActivity)
            addItemDecoration(ItemDecoration(8))
            adapter = filmAdapter
            hasFixedSize()
        }
        getFilms()
    }

    override fun click(url: String) {
        intent = DetailActivity.showDetail(this@MainActivity, url)
        startActivityForResult(intent, 1)
    }

    private fun getFilms() {
        val films = mutableListOf<Film>()

        NetworkService.getSWApi()?.getFilms()?.enqueue(object : Callback<FilmsResponse> {
            override fun onResponse(call: Call<FilmsResponse>, response: Response<FilmsResponse>) {
                if (response.isSuccessful) {
                    val filmsResponse = response.body()
                    filmsResponse?.results?.forEach { films.add(it) }
                    films.sort()
                    filmAdapter.update(films)
                }
            }

            override fun onFailure(call: Call<FilmsResponse>, t: Throwable) =
                Toast.makeText(this@MainActivity, "Error!", Toast.LENGTH_SHORT).show()
        })
    }
}