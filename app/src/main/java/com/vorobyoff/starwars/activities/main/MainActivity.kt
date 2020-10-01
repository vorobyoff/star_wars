package com.vorobyoff.starwars.activities.main

import android.os.Bundle
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.vorobyoff.starwars.activities.details.DetailActivity
import com.vorobyoff.starwars.activities.main.adapters.FilmAdapter
import com.vorobyoff.starwars.activities.main.adapters.ItemTouchHelperCallback
import com.vorobyoff.starwars.activities.main.presenters.MainPresenter
import com.vorobyoff.starwars.activities.main.presenters.MainView
import com.vorobyoff.starwars.databinding.ActivityMainBinding
import com.vorobyoff.starwars.databinding.MainActionBarBinding
import com.vorobyoff.starwars.models.Film
import moxy.MvpAppCompatActivity
import moxy.presenter.InjectPresenter

class MainActivity : MvpAppCompatActivity(), MainView {
    @InjectPresenter
    lateinit var mainPresenter: MainPresenter
    private lateinit var mainBinding: ActivityMainBinding
    private val filmAdapter = FilmAdapter(
        { startActivity(DetailActivity.showDetail(this, url = it)) },
        { mainPresenter.saveFilm(film = it) })
    private val itemTouchHelper = ItemTouchHelper(ItemTouchHelperCallback(filmAdapter))

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mainBinding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(mainBinding.root)

        setSupportActionBar(MainActionBarBinding.inflate(layoutInflater).root)
        mainBinding.filmsRecyclerView.apply {
            layoutManager = LinearLayoutManager(this@MainActivity, RecyclerView.VERTICAL, false)
            itemTouchHelper.attachToRecyclerView(this)
            addItemDecoration(ItemDecoration(8))
            adapter = filmAdapter
            hasFixedSize()
        }
        mainPresenter.getFilms()
        mainBinding.favoriteFilmsButton.setOnClickListener { }
    }

    override fun setFilms(films: List<Film>) = filmAdapter.setFilms(films)
}