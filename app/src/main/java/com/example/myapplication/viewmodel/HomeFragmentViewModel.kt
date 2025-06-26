package com.example.myapplication.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.myapplication.App
import com.example.myapplication.data.Entity.Book
import com.example.myapplication.domain.Interactor
import java.util.concurrent.Executors
import javax.inject.Inject

class HomeFragmentViewModel : ViewModel() {
    val booksListLiveData: MutableLiveData<List<Book>> = MutableLiveData()


    @Inject
    lateinit var interactor: Interactor

    init {
        App.instance.dagger.inject(this)
        getBooks()
    }

    private fun getBooks() {
        interactor.getBooksFromApi(1, object : ApiCallback {
            override fun onSuccess(books: List<Book>) {
               booksListLiveData.postValue(books)
            }

            override fun onFailure() {
                Executors.newSingleThreadExecutor().execute {
                    booksListLiveData.postValue(interactor.getBooksFromDB())
                }
            }


        })
    }




    interface ApiCallback {
        fun onSuccess(books: List<Book>)
        fun onFailure()
    }
}