package com.vorobyoff.starwars.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.vorobyoff.starwars.R
import java.util.*

abstract class Adapter<T>(
    private var onItemClickListener: ((data: T) -> Unit)?,
    private inline val onItemSwipeListener: ((data: T) -> Unit)?
) : RecyclerView.Adapter<RecyclerView.ViewHolder>(), ItemTouchHelperAdapter {
    private var items: MutableList<T> = LinkedList()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        getViewHolder(
            LayoutInflater.from(parent.context).inflate(viewType, parent, false),
            viewType
        )

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) =
        (holder as Binder<T>).bind(items[position], onItemClickListener)

    override fun getItemCount() = items.count()

    override fun getItemViewType(position: Int) = getLayoutId(position, items[position])

    override fun onItemTouch(position: Int) {
        onItemSwipeListener?.invoke(items[position])
        when (getItemViewType(position)) {
            R.layout.film_item -> notifyItemChanged(position)
            R.layout.favorite_film_item -> {
                items.removeAt(position)
                notifyItemRemoved(position)
            }
        }
    }

    protected abstract fun getLayoutId(position: Int, objects: T): Int

    protected open fun getViewHolder(view: View, viewType: Int) =
        ViewHolderFactory.create(view, viewType)

    fun update(items: List<T>) {
        this.items = items as MutableList<T>
        notifyDataSetChanged()
    }

    internal interface Binder<T> {
        fun bind(data: T, clickListener: ((data: T) -> Unit)?)
    }
}