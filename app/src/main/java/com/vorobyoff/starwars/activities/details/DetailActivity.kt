package com.vorobyoff.starwars.activities.details

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.vorobyoff.starwars.activities.details.presenters.DetailPresenter
import com.vorobyoff.starwars.databinding.ActivityDetailBinding
import com.vorobyoff.starwars.models.Film

class DetailActivity : AppCompatActivity() {
    private lateinit var detailBinding: ActivityDetailBinding
    private lateinit var detailPresenter: DetailPresenter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        detailBinding = ActivityDetailBinding.inflate(layoutInflater)
        setContentView(detailBinding.root)

        detailPresenter = DetailPresenter(this)
        intent.getStringExtra(URL_KEY)?.let { detailPresenter.show(it) }
    }

    fun show(film: Film) {
        detailBinding.titleTextView.text = film.title
        detailBinding.episodeIdTextView.text = film.episodeId.toString()
        detailBinding.directorTextView.text = film.director
        detailBinding.producerTextView.text = film.producer
        detailBinding.releaseDateTextView.text = film.releaseDate
        detailBinding.openingCrawlTextView.text = film.openingCrawl
    }

    companion object {
        private const val URL_KEY = "url_key"

        fun showDetail(context: Context, url: String): Intent {
            val intent = Intent(context, DetailActivity::class.java)
            return intent.putExtra(URL_KEY, url)
        }
    }
}