package com.vorobyoff.starwars.activities

import android.os.Bundle
import android.view.View
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.vorobyoff.starwars.R
import com.vorobyoff.starwars.adapters.Adapter
import com.vorobyoff.starwars.adapters.ItemTouchHelperCallback
import com.vorobyoff.starwars.databinding.ActivityMainBinding
import com.vorobyoff.starwars.databinding.BottomSheetBinding
import com.vorobyoff.starwars.databinding.MainActionBarBinding
import com.vorobyoff.starwars.models.Film
import com.vorobyoff.starwars.presenters.MainPresenter
import com.vorobyoff.starwars.views.MainView
import kotlinx.android.synthetic.main.bottom_sheet.view.*
import moxy.MvpAppCompatActivity
import moxy.presenter.InjectPresenter
import moxy.presenter.ProvidePresenter

class MainActivity : MvpAppCompatActivity(), MainView {
    @InjectPresenter
    lateinit var presenter: MainPresenter
    private lateinit var binding: ActivityMainBinding
    private lateinit var dialog: BottomSheetDialog
    private lateinit var filmAdapter: Adapter<Film>
    private var favoriteFilmsAdapter: Adapter<Film>? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setSupportActionBar(MainActionBarBinding.inflate(layoutInflater).root)

        dialog = BottomSheetDialog(this@MainActivity)

        binding.apply {
            filmsRecyclerView.apply {
                filmAdapter = getFilmAdapter
                layoutManager = LinearLayoutManager(context, RecyclerView.VERTICAL, false)
                ItemTouchHelper(ItemTouchHelperCallback(filmAdapter)).attachToRecyclerView(this)
                addItemDecoration(ItemDecoration(8))
                adapter = filmAdapter
                setHasFixedSize(true)
            }
        }

        presenter.getFilms()
        presenter.favoriteFilms.observe(this@MainActivity, { films ->
            when (true) {
                films.isNullOrEmpty() -> {
                    binding.favoriteFilmsButton.apply {
                        visibility = View.GONE
                        isClickable = false
                        dialog.dismiss()
                        return@observe
                    }
                }
                favoriteFilmsAdapter == null -> createBottomSheet()
            }
            favoriteFilmsAdapter?.update(films)
        })
    }

    @ProvidePresenter
    fun provideMainPresenter(): MainPresenter = MainPresenter(applicationContext)

    override fun set(films: List<Film>) = filmAdapter.update(films)

    private fun createBottomSheet() {
        val bottomSheetView = BottomSheetBinding.inflate(layoutInflater).root
        dialog.setContentView(bottomSheetView)

        bottomSheetView.favorite_films_list.apply {
            favoriteFilmsAdapter = getFavoriteFilmsAdapter
            layoutManager = LinearLayoutManager(applicationContext, RecyclerView.VERTICAL, false)
            ItemTouchHelper(ItemTouchHelperCallback(favoriteFilmsAdapter as Adapter<Film>))
                .attachToRecyclerView(this)
            addItemDecoration(ItemDecoration(4))
            adapter = favoriteFilmsAdapter
            setHasFixedSize(true)
        }

        binding.favoriteFilmsButton.apply {
            setOnClickListener { dialog.show() }
            visibility = View.VISIBLE
            isClickable = true
        }
    }

    private val getFilmAdapter = object : Adapter<Film>(
        { startActivity(DetailActivity.showDetail(applicationContext, it.url)) },
        { presenter.insert(it) }
    ) {
        override fun getLayoutId(position: Int, objects: Film) = R.layout.film_item
    }

    private val getFavoriteFilmsAdapter = object : Adapter<Film>(
        { startActivity(DetailActivity.showDetail(applicationContext, it.url)) },
        { presenter.delete(it) }
    ) {
        override fun getLayoutId(position: Int, objects: Film) = R.layout.favorite_film_item
    }
}