package com.vorobyoff.starwars.activities.main.adapters

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

    class FilmViewHolder(view: View) :
        RecyclerView.ViewHolder(view), FilmAdapter.Binder<Film> {
        override fun bind(data: Film, clickListener: ((data: Film) -> Unit)?) {
            itemView.apply {
                item_title_text_view.text = data.title
                episode_id_text_view.text = "${data.episodeId}"
                director_text_view.text = data.director
                producer_text_view.text = data.producer
            }

            clickListener?.let { func -> itemView.setOnClickListener { func.invoke(data) } }
        }
    }

    class FavoriteFilmViewHolder(view: View) :
        RecyclerView.ViewHolder(view), FilmAdapter.Binder<Film> {
        override fun bind(data: Film, clickListener: ((data: Film) -> Unit)?) {
            itemView.apply {
                title_text_view.text = data.title
                episode_id_text_view.text = "${data.episodeId}"
                release_date_text_view.text = data.releaseDate
            }

            clickListener?.let { func -> itemView.setOnClickListener { func.invoke(data) } }
        }
    }
}