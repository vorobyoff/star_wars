package com.vorobyoff.starwars.activities.main.adapter

import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.RecyclerView

class SwipeToSaveFilmCallback(private val adapter: FilmAdapter) :
    ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.START) {
    override fun onMove(
        recyclerView: RecyclerView,
        viewHolder: RecyclerView.ViewHolder,
        target: RecyclerView.ViewHolder
    ): Boolean {
        TODO("Not yet implemented")
    }

    override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
        adapter.save(viewHolder.adapterPosition)
    }
}