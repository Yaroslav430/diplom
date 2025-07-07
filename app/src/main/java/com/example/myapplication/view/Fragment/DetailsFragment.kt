package com.example.myapplication.view.Fragment

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.bumptech.glide.Glide
import com.example.myapplication.R
import com.example.myapplication.data.ApiConstants
import com.example.myapplication.data.Entity.Book
import com.example.myapplication.databinding.FragmentDetailsBinding


class DetailsFragment : Fragment() {
    private lateinit var book: Book
    private lateinit var binding : FragmentDetailsBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentDetailsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setBooksDetails()

        binding.detailsFabFavorites.setOnClickListener {
            if (!book.isInFavorites) {
                binding.detailsFabFavorites.setImageResource(R.drawable.ic_baseline_favorite_24)
                book.isInFavorites = true
            } else {
                binding.detailsFabFavorites.setImageResource(R.drawable.ic_baseline_favorite_border_24)
                book.isInFavorites = false
            }
        }

        binding.detailsFabShare.setOnClickListener {
            val intent = Intent()
            intent.action = Intent.ACTION_SEND
            intent.putExtra(
                Intent.EXTRA_TEXT,
                "Check out this book: ${book.title} \n\n ${book.description}"
            )
            intent.type = "text/plain"
            startActivity(Intent.createChooser(intent, "Share To:"))
        }
    }

    private fun setBooksDetails() {
        @Suppress("DEPRECATION")
        book = arguments?.get("book") as Book

        binding.detailsToolbar.title = book.title
        Glide.with(this)
            .load(ApiConstants.IMAGES_URL + "w780" + book.poster)
            .centerCrop()
            .into(binding.detailsPoster)
        binding.detailsDescription.text = book.description

        binding.detailsFabFavorites.setImageResource(
            if (book.isInFavorites) R.drawable.ic_baseline_favorite_24
            else R.drawable.ic_baseline_favorite_border_24
        )
    }

}



