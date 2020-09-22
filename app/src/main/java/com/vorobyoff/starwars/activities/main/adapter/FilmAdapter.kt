package com.vorobyoff.starwars.activities.main.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.vorobyoff.starwars.databinding.FilmItemBinding
import com.vorobyoff.starwars.models.Film

class FilmAdapter(private val onItemClickListener: OnItemClickListener) :
    RecyclerView.Adapter<FilmHolder>(),
    OnItemClickListener {
    private val films = mutableListOf<Film>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        FilmHolder(FilmItemBinding.inflate(LayoutInflater.from(parent.context)))

    override fun onBindViewHolder(holder: FilmHolder, position: Int) {
        holder.bind(
            films[position].title,
            films[position].episodeId,
            films[position].director,
            films[position].producer
        )
        holder.itemView.setOnClickListener {
            onItemClickListener.onItemClick(films[position].url)
        }
    }

    override fun getItemCount() = films.size

    override fun onItemClick(url: String) {
        onItemClickListener.onItemClick(url)
    }

    fun update(data: List<Film>) {
        films.clear()
        films.addAll(data)
        notifyDataSetChanged()
    }
}