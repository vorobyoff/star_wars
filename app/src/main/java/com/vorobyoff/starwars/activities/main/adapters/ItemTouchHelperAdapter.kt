package com.vorobyoff.starwars.activities.main.adapters

@FunctionalInterface
interface ItemTouchHelperAdapter {
    fun onItemTouch(position: Int)
}