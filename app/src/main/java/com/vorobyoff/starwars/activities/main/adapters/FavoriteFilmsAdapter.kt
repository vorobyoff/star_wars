package com.vorobyoff.starwars.activities.main.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.vorobyoff.starwars.databinding.FavoriteFilmItemBinding
import com.vorobyoff.starwars.models.Film
import java.util.*

class FavoriteFilmsAdapter : RecyclerView.Adapter<FavoriteFilmsAdapter.Holder>() {

    private val favoritesFilms: MutableList<Film> = LinkedList()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        Holder(FavoriteFilmItemBinding.inflate(LayoutInflater.from(parent.context), parent, false))


    override fun onBindViewHolder(holder: Holder, position: Int) {
        holder.bind(favoritesFilms[position])
    }


    override fun getItemCount() = favoritesFilms.count()

    fun setFilms(films: List<Film>) {
        favoritesFilms.clear()
        favoritesFilms.addAll(films)
        notifyDataSetChanged()
    }

    inner class Holder(val itemBinding: FavoriteFilmItemBinding) :
        RecyclerView.ViewHolder(itemBinding.root) {
        fun bind(film: Film) {
            itemBinding.titleTextView.text = film.title
        }
    }
}