package com.vorobyoff.starwars.models

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.Ignore
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity(tableName = "films_table")
data class Film(
    @ColumnInfo(name = "id") @PrimaryKey(autoGenerate = true) var id: Int = 0,

    @SerializedName("title")
    @ColumnInfo(name = "title") var title: String = "",

    @SerializedName("episode_id")
    @ColumnInfo(name = "episode_id") var episodeId: Int = 0,

    @SerializedName("director")
    @ColumnInfo(name = "director") var director: String = "",

    @SerializedName("producer")
    @ColumnInfo(name = "producer") var producer: String = "",

    @SerializedName("release_date")
    @ColumnInfo(name = "realise_date") var releaseDate: String = "",

    @ColumnInfo(name = "url")
    @SerializedName("url") var url: String = "",

    @SerializedName("opening_crawl")
    @Ignore val openingCrawl: String = "",
) : Comparable<Film> {
    override fun compareTo(other: Film): Int {
        if (episodeId > other.episodeId) return 1
        return if (episodeId < other.episodeId) -1 else 0
    }
}