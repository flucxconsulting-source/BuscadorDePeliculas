package com.formacion.buscadordepeliculas.api

import com.google.gson.annotations.SerializedName

data class MovieItem(
    @SerializedName("Title") val title: String,
    @SerializedName("Year") val year: String,
    @SerializedName("imdbID") val imdbID: String,
    @SerializedName("Poster") val poster: String,
)
