package com.example.myapplication.data

import com.example.myapplication.data.Entity.Book
import com.example.myapplication.data.dao.BookDao
import java.util.concurrent.Executors

class MainRepository(private val bookDao: BookDao) {

    fun putToDb(books: List<Book>) {
        Executors.newSingleThreadExecutor().execute {
            bookDao.insertAll(books)
        }
    }

    fun getAllFromDB(): List<Book> {
        return bookDao.getCachedBooks()
    }
}