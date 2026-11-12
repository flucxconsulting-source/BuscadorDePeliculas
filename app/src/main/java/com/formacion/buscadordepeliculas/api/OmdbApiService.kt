package com.formacion.buscadordepeliculas.api

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface OmdbApiService {
    @GET(".")
    fun searchMovies(
        @Query("s") query: String,
        @Query("apikey") apiKey: String,
    ): Call<SearchResponse>

    @GET(".")
    fun getMovieDetail(
        @Query("i") imdbId: String,
        @Query("apikey") apiKey: String,
    ): Call<MovieDetail>
}
