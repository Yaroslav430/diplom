package com.example.myapplication.data

import com.example.myapplication.data.Entity.TmdbResults
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface TmdbApi {
    @GET("v1/public/comics?")
    fun getBooks(
        @Path("category") category: String,
        @Query("api_key") apiKey: String,
        @Query("language") language: String,
        @Query("page") page: Int
    ): Call<TmdbResults>
}