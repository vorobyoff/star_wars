package com.vorobyoff.starwars.activities.main.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.vorobyoff.starwars.databinding.FilmItemBinding
import com.vorobyoff.starwars.models.Film
import java.util.*

class FilmAdapter(private val onItemClickListener: OnItemClickListener) :
    RecyclerView.Adapter<FilmHolder>(),
    OnItemClickListener {
    private val films: MutableList<Film> = LinkedList()
    var swipedFilm: Film? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        FilmHolder(FilmItemBinding.inflate(LayoutInflater.from(parent.context), parent, false))

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

    override fun getItemCount() = films.count()

    override fun onItemClick(url: String) {
        onItemClickListener.onItemClick(url)
    }

    fun save(position: Int) {
        swipedFilm = films[position]
    }

    fun setFilms(data: List<Film>) {
        films.clear()
        films.addAll(data)
        notifyDataSetChanged()
    }
}