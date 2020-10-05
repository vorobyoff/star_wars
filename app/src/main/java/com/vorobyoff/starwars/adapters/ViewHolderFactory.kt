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
                item_title_text_view.append(data.title)
                episode_id_text_view.append(" ${data.episodeId}")
                director_text_view.append(" ${data.director}")
                producer_text_view.append(" ${data.producer}")
            }

            clickListener?.let { func -> itemView.setOnClickListener { func.invoke(data) } }
        }
    }

    class FavoriteFilmViewHolder(view: View) : RecyclerView.ViewHolder(view), Adapter.Binder<Film> {
        override fun bind(data: Film, clickListener: ((data: Film) -> Unit)?) {
            itemView.apply {
                title_text_view.append(data.title)
                episode_id_text_view.append(" ${data.episodeId}")
                release_date_text_view.append(" ${data.releaseDate}")
            }

            clickListener?.let { func -> itemView.setOnClickListener { func.invoke(data) } }
        }
    }
}