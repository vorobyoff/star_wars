package com.vorobyoff.starwars.activities.details

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.vorobyoff.starwars.activities.details.presenters.DetailPresenterImpl
import com.vorobyoff.starwars.activities.details.presenters.DetailView
import com.vorobyoff.starwars.databinding.ActivityDetailBinding
import com.vorobyoff.starwars.models.Film

class DetailActivity : AppCompatActivity(), DetailView {
    private lateinit var detailBinding: ActivityDetailBinding
    private val detailPresenterImpl = DetailPresenterImpl()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        detailBinding = ActivityDetailBinding.inflate(layoutInflater)
        setContentView(detailBinding.root)

        detailPresenterImpl.attachView(this)
        intent.getStringExtra(URL_KEY)?.let { detailPresenterImpl.getData(it) }
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