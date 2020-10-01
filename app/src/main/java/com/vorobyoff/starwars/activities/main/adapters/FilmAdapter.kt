package com.vorobyoff.starwars.activities.main.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.vorobyoff.starwars.databinding.FilmItemBinding
import com.vorobyoff.starwars.models.Film
import java.util.*

class FilmAdapter(
    private val onItemClick: (url: String) -> Unit,
    private val onItemSwipe: (film: Film) -> Unit
) : RecyclerView.Adapter<FilmHolder>(), ItemTouchHelperAdapter {
    private val films: MutableList<Film> = LinkedList()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        FilmHolder(FilmItemBinding.inflate(LayoutInflater.from(parent.context), parent, false))

    override fun onBindViewHolder(holder: FilmHolder, position: Int) = holder.run {
        bind(films[position])
        itemView.setOnClickListener { onItemClick(films[position].url) }
    }

    override fun getItemCount() = films.count()

    override fun onItemTouch(position: Int) {
        onItemSwipe(films[position])
        notifyItemChanged(position)
    }

    fun setFilms(data: List<Film>) {
        films.clear()
        films.addAll(data)
        notifyDataSetChanged()
    }
}