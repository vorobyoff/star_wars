package com.vorobyoff.starwars.activities.main.adapter

import androidx.recyclerview.widget.RecyclerView
import com.vorobyoff.starwars.databinding.FilmItemBinding

data class FilmHolder(val itemBinding: FilmItemBinding) :
    RecyclerView.ViewHolder(itemBinding.root) {
    fun bind(title: String, episodeId: Int, director: String, producer: String) {
        itemBinding.apply {
            this.itemTitleTextView.text = title
            this.episodeIdTextView.text = episodeId.toString()
            this.directorTextView.text = director
            this.producerTextView.text = producer
        }
    }
}