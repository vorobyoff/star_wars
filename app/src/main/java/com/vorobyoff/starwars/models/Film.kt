package com.vorobyoff.starwars.models

import com.google.gson.annotations.SerializedName

data class Film(
    @SerializedName("title")
    val title: String,
    @SerializedName("episode_id")
    val episodeId: Int,
    @SerializedName("opening_crawl")
    val openingCrawl: String,
    @SerializedName("director")
    val director: String,
    @SerializedName("producer")
    val producer: String,
    @SerializedName("release_date")
    val releaseDate: String,
    @SerializedName("characters")
    val characters: List<String>,
    @SerializedName("planets")
    val planets: List<String>,
    @SerializedName("starships")
    val starShips: List<String>,
    @SerializedName("vehicles")
    val vehicles: List<String>,
    @SerializedName("species")
    val species: List<String>,
    @SerializedName("url")
    val url: String,
) : Comparable<Film> {
    override fun compareTo(other: Film): Int {
        if (episodeId > other.episodeId) return 1
        return if (episodeId < other.episodeId) -1 else 0
    }
}