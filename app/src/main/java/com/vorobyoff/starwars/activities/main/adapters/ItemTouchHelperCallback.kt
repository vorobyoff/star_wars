package com.vorobyoff.starwars.activities.main.adapters

import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.ItemTouchHelper.START
import androidx.recyclerview.widget.RecyclerView

class ItemTouchHelperCallback(private val adapter: FilmAdapter) : ItemTouchHelper.Callback() {
    override fun getMovementFlags(recyclerView: RecyclerView, viewHolder: RecyclerView.ViewHolder) =
        makeMovementFlags(0, START)

    override fun isLongPressDragEnabled() = false

    override fun onMove(
        recyclerView: RecyclerView,
        viewHolder: RecyclerView.ViewHolder,
        target: RecyclerView.ViewHolder
    ) = false

    override fun isItemViewSwipeEnabled() = true

    override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) =
        adapter.onItemTouch(viewHolder.adapterPosition)
}