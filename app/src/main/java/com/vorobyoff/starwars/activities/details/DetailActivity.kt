package com.vorobyoff.starwars.activities.details

import android.content.Context
import android.content.Intent
import android.os.Bundle
import com.vorobyoff.starwars.activities.details.presenters.DetailPresenter
import com.vorobyoff.starwars.activities.details.presenters.DetailView
import com.vorobyoff.starwars.databinding.ActivityDetailBinding
import com.vorobyoff.starwars.models.Film
import moxy.MvpAppCompatActivity
import moxy.presenter.InjectPresenter

class DetailActivity : MvpAppCompatActivity(), DetailView {
    @InjectPresenter
    lateinit var detailPresenter: DetailPresenter
    private lateinit var detailBinding: ActivityDetailBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        detailBinding = ActivityDetailBinding.inflate(layoutInflater)
        setContentView(detailBinding.root)
        detailPresenter.getData(intent.getStringExtra(URL_KEY) ?: "")
    }

    override fun show(film: Film) = detailBinding.run {
        titleTextView.text = film.title
        episodeIdTextView.append(" ${film.episodeId}")
        directorTextView.append(" ${film.director}")
        producerTextView.append(" ${film.producer}")
        releaseDateTextView.append(" ${film.releaseDate}")
        openingCrawlTextView.text = film.openingCrawl
    }

    companion object {
        private const val URL_KEY = "url_key"
        fun showDetail(context: Context, url: String): Intent {
            val intent = Intent(context, DetailActivity::class.java)
            return intent.putExtra(URL_KEY, url)
        }
    }
}