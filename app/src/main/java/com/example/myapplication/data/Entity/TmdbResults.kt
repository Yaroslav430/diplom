package com.example.myapplication.data.Entity

import com.google.gson.annotations.SerializedName

data class TmdbResults(
    @SerializedName("page")
    val page: Int,
    @SerializedName("results")
    val tmdbBooks: List<TmdbBook>,
    @SerializedName("total_pages")
    val totalPages: Int,
    @SerializedName("total_results")
    val totalResults: Int
)