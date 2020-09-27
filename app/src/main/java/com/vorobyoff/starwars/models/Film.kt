package com.vorobyoff.starwars.models

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.Ignore
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity(tableName = "films_table")
data class Film(
    @PrimaryKey(autoGenerate = true)

    @SerializedName("title")
    @ColumnInfo(name = "title") val title: String,

    @SerializedName("episode_id")
    @ColumnInfo(name = "episode_id") val episodeId: Int,

    @SerializedName("director")
    @ColumnInfo(name = "director") val director: String,

    @SerializedName("producer")
    @ColumnInfo(name = "producer") val producer: String,

    @SerializedName("release_date")
    @ColumnInfo(name = "realise_date") val releaseDate: String,

    @ColumnInfo(name = "url")
    @SerializedName("url") val url: String,

    @SerializedName("opening_crawl")
    @Ignore val openingCrawl: String,
) : Comparable<Film> {
    override fun compareTo(other: Film): Int {
        if (episodeId > other.episodeId) return 1
        return if (episodeId < other.episodeId) -1 else 0
    }
}