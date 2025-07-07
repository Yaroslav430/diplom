package com.example.myapplication.view.Fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.myapplication.data.Entity.Book
import com.example.myapplication.databinding.FragmentFavoritesBinding
import com.example.myapplication.view.MainActivity
import com.example.myapplication.view.rv_adapters.BookListRecyclerAdapter

class FavoritesFragment : Fragment() {
    private lateinit var bookAdapter: BookListRecyclerAdapter
    private lateinit var binding: FragmentFavoritesBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentFavoritesBinding.inflate(inflater, container, false)
        return binding.root

    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val favoritesList: List<Book> = emptyList()

        binding.favoritesRecycler.apply {
            bookAdapter =
                BookListRecyclerAdapter(object : BookListRecyclerAdapter.OnItemClickListener {
                    override fun click(book: Book) {
                        (requireActivity() as MainActivity).launchDetailsFragment(book)
                    }
                })
            adapter = bookAdapter
            layoutManager = LinearLayoutManager(requireContext())


        }
        bookAdapter.addItems(favoritesList)
    }

}





