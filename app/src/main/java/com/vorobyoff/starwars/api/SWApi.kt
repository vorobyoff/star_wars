package com.vorobyoff.starwars.api

import com.vorobyoff.starwars.models.Film
import com.vorobyoff.starwars.models.FilmsResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface SWApi {
    @GET("films/")
    fun getFilms(): Call<FilmsResponse>

    @GET("{url}")
    fun getFilm(@Path("url") url: String): Call<Film>
}