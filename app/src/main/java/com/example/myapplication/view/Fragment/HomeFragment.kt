package com.example.myapplication.view.Fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.SearchView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.myapplication.pagination.PaginationScrollListener
import com.example.myapplication.data.Entity.Book
import com.example.myapplication.databinding.FragmentHomeBinding
import com.example.myapplication.view.MainActivity
import com.example.myapplication.view.rv_adapters.BookListRecyclerAdapter
import com.example.myapplication.viewmodel.HomeFragmentViewModel
import java.util.Locale


class HomeFragment : Fragment() {
    private val viewModel by lazy {
        ViewModelProvider.NewInstanceFactory().create(HomeFragmentViewModel::class.java)
    }
    private lateinit var bookAdapter: BookListRecyclerAdapter
    private lateinit var binding: FragmentHomeBinding
    private var booksDataBase = listOf<Book>()
        set(value) {
            if (field == value) return
            field = value
            bookAdapter.addItems(field)
        }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        retainInstance = true
    }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root

    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initSearchView()
        initRecycler()
        addScrollListener()
        viewModel.booksListLiveData.observe(viewLifecycleOwner, Observer<List<Book>> {
            booksDataBase = it
            bookAdapter.addItems(it)
        })

    }



    private fun initSearchView() {
        binding.searchView.setOnClickListener {
            binding.searchView.isIconified = false
        }

        binding.searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                return true
            }

            override fun onQueryTextChange(newText: String): Boolean {
                if (newText.isEmpty()) {
                    bookAdapter.addItems(booksDataBase)
                    return true
                }
                val result = booksDataBase.filter {
                    it.title.lowercase(Locale.getDefault())
                        .contains(newText.lowercase(Locale.getDefault()))
                }
                bookAdapter.addItems(result)
                return true
            }
        })
    }


    private fun initRecycler() {
        binding.mainRecycler.apply {
            bookAdapter =
                BookListRecyclerAdapter(object : BookListRecyclerAdapter.OnItemClickListener {
                    override fun click(book: Book) {
                        (requireActivity() as MainActivity).launchDetailsFragment(book)
                    }
                })

            adapter = bookAdapter
            layoutManager = LinearLayoutManager(requireContext())

        }
        }private var isLoadingEmails = false

    private fun addScrollListener() {
        binding.mainRecycler.addOnScrollListener(object :
            PaginationScrollListener(binding.mainRecycler.layoutManager as LinearLayoutManager) {
            override fun loadMoreItems() {
                viewModel.booksListLiveData
            }

            override fun isLastPage() = viewModel.booksListLiveData.isInitialized

            override fun isLoading() = isLoadingEmails
        })
    }
    }















