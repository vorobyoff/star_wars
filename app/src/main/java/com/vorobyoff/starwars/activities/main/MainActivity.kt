package com.vorobyoff.starwars.activities.main

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.vorobyoff.starwars.activities.details.DetailActivity
import com.vorobyoff.starwars.activities.main.adapter.OnItemClickListener
import com.vorobyoff.starwars.activities.main.adapter.FilmAdapter
import com.vorobyoff.starwars.activities.main.presenters.MainPresenter
import com.vorobyoff.starwars.activities.main.presenters.Presenter
import com.vorobyoff.starwars.databinding.ActivityMainBinding
import com.vorobyoff.starwars.databinding.MainActionBarBinding
import com.vorobyoff.starwars.models.Film

class MainActivity : AppCompatActivity(), OnItemClickListener {
    private lateinit var mainBinding: ActivityMainBinding
    private lateinit var filmAdapter: FilmAdapter
    private lateinit var mainPresenter: Presenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mainBinding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(mainBinding.root)
        setSupportActionBar(MainActionBarBinding.inflate(layoutInflater).root)

        mainPresenter = MainPresenter(this)
        filmAdapter = FilmAdapter(this)
        mainBinding.filmsRecyclerView.apply {
            layoutManager = LinearLayoutManager(this@MainActivity)
            addItemDecoration(ItemDecoration(8))
            adapter = filmAdapter
            hasFixedSize()
        }
        mainPresenter.update()
    }

    override fun onItemClick(url: String) {
        intent = DetailActivity.showDetail(this@MainActivity, url)
        startActivityForResult(intent, 1)
    }

    fun update(films: List<Film>) {
        filmAdapter.update(films)
    }
}