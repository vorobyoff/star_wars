package com.vorobyoff.starwars.activities.main

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.vorobyoff.starwars.R
import com.vorobyoff.starwars.activities.details.DetailActivity
import com.vorobyoff.starwars.activities.main.adapters.FavoriteFilmsAdapter
import com.vorobyoff.starwars.activities.main.adapters.FilmAdapter
import com.vorobyoff.starwars.activities.main.adapters.ItemTouchHelperCallback
import com.vorobyoff.starwars.activities.main.presenters.MainPresenter
import com.vorobyoff.starwars.activities.main.presenters.MainView
import com.vorobyoff.starwars.databinding.ActivityMainBinding
import com.vorobyoff.starwars.databinding.MainActionBarBinding
import com.vorobyoff.starwars.models.Film
import kotlinx.android.synthetic.main.bottom_sheet.view.*
import moxy.MvpAppCompatActivity
import moxy.presenter.InjectPresenter
import moxy.presenter.ProvidePresenter

class MainActivity : MvpAppCompatActivity(), MainView {
    @InjectPresenter
    lateinit var mainPresenter: MainPresenter

    @ProvidePresenter
    fun provideMainPresenter(): MainPresenter = MainPresenter(this@MainActivity)

    private lateinit var mainBinding: ActivityMainBinding
    private val filmAdapter = FilmAdapter(
        { startActivity(DetailActivity.showDetail(this, url = it)) },
        { mainPresenter.saveFilm(film = it) })
    private val favoriteFilmAdapter = FavoriteFilmsAdapter()
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

        mainPresenter.favoriteFilms.observe(this) {
            if (it.isNullOrEmpty()) {
                mainBinding.favoriteFilmsButton.visibility = View.GONE
            } else {
                mainBinding.favoriteFilmsButton.visibility = View.VISIBLE
                favoriteFilmAdapter.setFilms(it)
            }
        }
        mainBinding.favoriteFilmsButton.setOnClickListener {
            val bottomSheetDialog = BottomSheetDialog(this)
            val bottomSheetView = LayoutInflater.from(applicationContext)
                .inflate(R.layout.bottom_sheet, findViewById(R.id.bottom_sheet_container))
            bottomSheetDialog.setContentView(bottomSheetView)
            bottomSheetView.favorite_films_list.apply {
                layoutManager = LinearLayoutManager(this@MainActivity, RecyclerView.VERTICAL, false)
                adapter = favoriteFilmAdapter
                hasFixedSize()
            }
            bottomSheetDialog.show()
        }
    }

    override fun setFilms(films: List<Film>) = filmAdapter.setFilms(films)
}