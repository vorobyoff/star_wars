package com.vorobyoff.starwars.models

import androidx.annotation.NonNull
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity(tableName = "films_table")
data class Film(
    @SerializedName("title")
    @PrimaryKey @NonNull @ColumnInfo(name = "film_title") var title: String,
    @SerializedName("episode_id")
    @ColumnInfo(name = "episode_id") var episodeId: Int,
    @SerializedName("opening_crawl")
    @ColumnInfo(name = "opening_crawl") var openingCrawl: String,
    @SerializedName("director")
    @ColumnInfo(name = "director") var director: String,
    @SerializedName("producer")
    @ColumnInfo(name = "producer") var producer: String,
    @SerializedName("release_date")
    @ColumnInfo(name = "realise_date") var releaseDate: String,
    @SerializedName("characters")
    var characters: List<String>,
    @SerializedName("planets")
    var planets: List<String>,
    @SerializedName("starships")
    var starShips: List<String>,
    @SerializedName("vehicles")
    var vehicles: List<String>,
    @SerializedName("species")
    var species: List<String>,
    @SerializedName("url")
    var url: String,
) : Comparable<Film> {
    override fun compareTo(other: Film): Int {
        if (episodeId > other.episodeId) return 1
        return if (episodeId < other.episodeId) -1 else 0
    }
}