package com.vorobyoff.starwars.activities.main.adapters

import androidx.recyclerview.widget.RecyclerView
import com.vorobyoff.starwars.databinding.FilmItemBinding
import com.vorobyoff.starwars.models.Film

data class FilmHolder(val itemBinding: FilmItemBinding) :
    RecyclerView.ViewHolder(itemBinding.root) {
    fun bind(film: Film) {
        itemBinding.apply {
            itemTitleTextView.text = film.title
            episodeIdTextView.text = film.episodeId.toString()
            directorTextView.text = film.director
            producerTextView.text = film.producer
        }
    }
}