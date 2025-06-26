package com.example.myapplication.view.rv_viewholders

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.myapplication.data.ApiConstants
import com.example.myapplication.databinding.ComicsItemBinding
import com.example.myapplication.data.Entity.Book


class BookViewHolder(comicsItem: View ) : RecyclerView.ViewHolder(comicsItem) {
    private val comicsItemBinding = ComicsItemBinding.bind(comicsItem)
    private val title = comicsItemBinding.title
    private val poster = comicsItemBinding.poster
    private val description = comicsItemBinding.description


    fun bind(book: Book) {
        title.text = book.title
        Glide.with(itemView)
            .load(ApiConstants.IMAGES_URL + "w342" + book.poster)
            .centerCrop()
            .into(poster)
        description.text = book.description

    }
}