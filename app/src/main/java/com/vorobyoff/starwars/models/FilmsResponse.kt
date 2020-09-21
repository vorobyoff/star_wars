package com.vorobyoff.starwars.models

import com.google.gson.annotations.SerializedName

data class FilmsResponse(
    @SerializedName("results") val results: List<Film>
)