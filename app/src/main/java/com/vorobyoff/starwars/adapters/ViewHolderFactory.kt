package com.vorobyoff.starwars.adapters

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.vorobyoff.starwars.R
import com.vorobyoff.starwars.models.Film
import kotlinx.android.synthetic.main.favorite_film_item.view.*
import kotlinx.android.synthetic.main.film_item.view.*
import kotlinx.android.synthetic.main.film_item.view.episode_id_text_view

object ViewHolderFactory {
    fun create(view: View, viewType: Int): RecyclerView.ViewHolder {
        return when (viewType) {
            R.layout.film_item -> FilmViewHolder(view)
            R.layout.favorite_film_item -> FavoriteFilmViewHolder(view)
            else -> throw Exception("Wrong view type")
        }
    }

    class FilmViewHolder(view: View) : RecyclerView.ViewHolder(view), Adapter.Binder<Film> {
        override fun bind(data: Film, clickListener: ((data: Film) -> Unit)?) {
            itemView.apply {
                item_title_text_view.text = data.title
                val episode = "${resources.getString(R.string.episode)} ${data.episodeId}"
                episode_id_text_view.text = episode
                val director = "${resources.getString(R.string.director)} ${data.director}"
                director_text_view.text = director
                val producer = "${resources.getString(R.string.producer)} ${data.producer}"
                producer_text_view.text = producer
            }

            clickListener?.let { func -> itemView.setOnClickListener { func.invoke(data) } }
        }
    }

    class FavoriteFilmViewHolder(view: View) : RecyclerView.ViewHolder(view), Adapter.Binder<Film> {
        override fun bind(data: Film, clickListener: ((data: Film) -> Unit)?) {
            itemView.apply {
                title_text_view.text = data.title
                val episode = "${resources.getString(R.string.episode)} ${data.episodeId}"
                episode_id_text_view.text = episode
                val date = "${resources.getString(R.string.realise_date)} ${data.releaseDate}"
                release_date_text_view.text = date
            }

            clickListener?.let { func -> itemView.setOnClickListener { func.invoke(data) } }
        }
    }
}