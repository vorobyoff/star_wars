package com.vorobyoff.starwars.activities.details

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.vorobyoff.starwars.api.NetworkService
import com.vorobyoff.starwars.databinding.ActivityDetailBinding
import com.vorobyoff.starwars.models.Film
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class DetailActivity : AppCompatActivity() {
    private lateinit var detailBinding: ActivityDetailBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        detailBinding = ActivityDetailBinding.inflate(layoutInflater)
        setContentView(detailBinding.root)

        getFilm(intent.getStringExtra(URL_KEY))
    }

    private fun getFilm(url: String?) {
        val validUrl = url?.let {
            val lastIndex = it.lastIndexOf("films/")
            it.substring(lastIndex)
        } ?: "Error!"

        NetworkService.getSWApi()?.getFilm(validUrl)?.enqueue(object : Callback<Film> {
            override fun onResponse(call: Call<Film>, response: Response<Film>) {
                if (response.isSuccessful) {
                    val film = response.body()
                    detailBinding.titleTextView.text = film?.title
                    detailBinding.episodeIdTextView.text = film?.episodeId.toString()
                    detailBinding.directorTextView.text = film?.director
                    detailBinding.producerTextView.text = film?.producer
                    detailBinding.releaseDateTextView.text = film?.releaseDate
                    detailBinding.openingCrawlTextView.text = film?.openingCrawl
                }
            }

            override fun onFailure(call: Call<Film>, t: Throwable) =
                Toast.makeText(this@DetailActivity, "Error!", Toast.LENGTH_SHORT).show()
        })
    }

    companion object {
        private const val URL_KEY = "url_key"

        fun showDetail(context: Context, url: String): Intent {
            val intent = Intent(context, DetailActivity::class.java)
            return intent.putExtra(URL_KEY, url)
        }
    }
}