package com.example.myapplication.data.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import com.example.myapplication.data.Entity.Book
import androidx.room.Query

@Dao
interface BookDao {

    @Query("SELECT * FROM cached_books")
    fun getCachedBooks(): List<Book>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAll(list: List<Book>)


}