package com.vorobyoff.starwars.activities.main

import android.os.Bundle
import android.view.View
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.vorobyoff.starwars.R
import com.vorobyoff.starwars.activities.details.DetailActivity
import com.vorobyoff.starwars.activities.main.adapters.FilmAdapter
import com.vorobyoff.starwars.activities.main.adapters.ItemTouchHelperCallback
import com.vorobyoff.starwars.activities.main.presenters.MainPresenter
import com.vorobyoff.starwars.activities.main.presenters.MainView
import com.vorobyoff.starwars.databinding.ActivityMainBinding
import com.vorobyoff.starwars.databinding.BottomSheetBinding
import com.vorobyoff.starwars.databinding.MainActionBarBinding
import com.vorobyoff.starwars.models.Film
import kotlinx.android.synthetic.main.bottom_sheet.view.*
import moxy.MvpAppCompatActivity
import moxy.presenter.InjectPresenter
import moxy.presenter.ProvidePresenter

class MainActivity : MvpAppCompatActivity(), MainView {
    @InjectPresenter
    lateinit var mainPresenter: MainPresenter
    private lateinit var filmAdapter: FilmAdapter<Film>
    private lateinit var mainBinding: ActivityMainBinding
    private lateinit var fFilmsAdapter: FilmAdapter<Film>
    private lateinit var bottomSheetDialog: BottomSheetDialog

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mainBinding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(mainBinding.root)
        setSupportActionBar(MainActionBarBinding.inflate(layoutInflater).root)

        filmAdapter = getFilmAdapter
        fFilmsAdapter = getFavoriteFilmsAdapter
        mainBinding.filmsRecyclerView.apply {
            layoutManager = LinearLayoutManager(this@MainActivity, RecyclerView.VERTICAL, false)
            ItemTouchHelper(ItemTouchHelperCallback(filmAdapter)).attachToRecyclerView(this)
            addItemDecoration(ItemDecoration(8))
            adapter = filmAdapter
            hasFixedSize()
        }
        mainPresenter.getFilms()

        mainPresenter.favoriteFilms.observe(this@MainActivity) {
            if (it.isNullOrEmpty()) {
                mainBinding.favoriteFilmsButton.visibility = View.GONE
            } else {
                mainBinding.favoriteFilmsButton.visibility = View.VISIBLE
                fFilmsAdapter.update(it)
            }
        }
        mainBinding.favoriteFilmsButton.setOnClickListener {
            bottomSheetDialog = BottomSheetDialog(this@MainActivity)
            val bottomSheetView = BottomSheetBinding.inflate(layoutInflater).root
            bottomSheetDialog.setContentView(bottomSheetView)

            bottomSheetView.favorite_films_list.apply {
                layoutManager = LinearLayoutManager(this@MainActivity, RecyclerView.VERTICAL, false)
                ItemTouchHelper(ItemTouchHelperCallback(fFilmsAdapter)).attachToRecyclerView(this)
                addItemDecoration(ItemDecoration(8))
                adapter = fFilmsAdapter
                hasFixedSize()
            }
            bottomSheetDialog.show()
        }
    }

    @ProvidePresenter
    fun provideMainPresenter(): MainPresenter = MainPresenter(this@MainActivity)

    override fun setFilms(films: List<Film>) = filmAdapter.update(films)

    private val getFilmAdapter = object : FilmAdapter<Film>(
        { startActivity(DetailActivity.showDetail(this@MainActivity, it.url)) },
        { mainPresenter.saveFilm(it) }
    ) {
        override fun getLayoutId(position: Int, objects: Film) = R.layout.film_item
    }

    private val getFavoriteFilmsAdapter = object : FilmAdapter<Film>(
        { DetailActivity.showDetail(this@MainActivity, it.url) },
        {
            mainPresenter.deleteFilm(it)
            if (mainPresenter.favoriteFilms.value?.isEmpty()!!) bottomSheetDialog.dismiss()
        }
    ) {
        override fun getLayoutId(position: Int, objects: Film) = R.layout.favorite_film_item
    }
}