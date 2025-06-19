package com.example.myapplication.utils

import com.example.myapplication.data.Entity.TmdbBook
import com.example.myapplication.data.Entity.Book

object Converter {
    fun convertApiListToDTOList(list: List<TmdbBook>?): List<Book> {
        val result = mutableListOf<Book>()
        list?.forEach {
            result.add(
                Book(
                title = it.title,
                poster = it.posterPath,
                description = it.overview,
                isInFavorites = false
            )
            )
        }
        return result
    }
}