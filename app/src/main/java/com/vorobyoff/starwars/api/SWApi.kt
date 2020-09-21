package com.vorobyoff.starwars.api

import com.vorobyoff.starwars.models.FilmsResponse
import retrofit2.Call
import retrofit2.http.GET

interface SWApi {
    @GET("films/")
    fun getFilms(): Call<FilmsResponse>
}