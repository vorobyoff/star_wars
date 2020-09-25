package com.vorobyoff.starwars.activities.main

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.vorobyoff.starwars.activities.details.DetailActivity
import com.vorobyoff.starwars.activities.main.adapter.OnItemClickListener
import com.vorobyoff.starwars.activities.main.adapter.FilmAdapter
import com.vorobyoff.starwars.activities.main.presenters.MainPresenterImpl
import com.vorobyoff.starwars.activities.main.presenters.MainView
import com.vorobyoff.starwars.databinding.ActivityMainBinding
import com.vorobyoff.starwars.databinding.MainActionBarBinding
import com.vorobyoff.starwars.models.Film

class MainActivity : AppCompatActivity(), OnItemClickListener, MainView {
    private val mainPresenter = MainPresenterImpl()
    private lateinit var mainBinding: ActivityMainBinding
    private lateinit var filmAdapter: FilmAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mainBinding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(mainBinding.root)
        setSupportActionBar(MainActionBarBinding.inflate(layoutInflater).root)

        mainPresenter.attachView(this)
        filmAdapter = FilmAdapter(this)
        mainBinding.filmsRecyclerView.apply {
            layoutManager = LinearLayoutManager(this@MainActivity)
            addItemDecoration(ItemDecoration(8))
            adapter = filmAdapter
            hasFixedSize()
        }
        mainPresenter.getFilms()
    }

    override fun onItemClick(url: String) {
        startActivity(DetailActivity.showDetail(this, url))
    }

    override fun setFilms(films: List<Film>) {
        filmAdapter.update(films)
    }
}