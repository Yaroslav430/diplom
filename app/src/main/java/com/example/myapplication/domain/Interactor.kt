package com.example.myapplication.domain
import com.example.myapplication.API
import com.example.myapplication.PreferenceProvider
import com.example.myapplication.data.Entity.Book
import com.example.myapplication.utils.Converter
import com.example.myapplication.data.Entity.TmdbResults
import com.example.myapplication.data.MainRepository
import com.example.myapplication.data.TmdbApi
import com.example.myapplication.viewmodel.HomeFragmentViewModel
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class Interactor(private val repo: MainRepository, private val retrofitService: TmdbApi , private val preferences: PreferenceProvider) {
    fun getBooksFromApi(page: Int, callback: HomeFragmentViewModel.ApiCallback) {
        retrofitService.getBooks(getDefaultCategoryFromPreferences(), API.KEY, "ru-RU", page).enqueue(object : Callback<TmdbResults> {
            override fun onResponse(call: Call<TmdbResults>, response: Response<TmdbResults>) {
                val list = Converter.convertApiListToDTOList(response.body()?.tmdbBooks)
                repo.putToDb(list)
                callback.onSuccess(list)
            }

            override fun onFailure(call: Call<TmdbResults>, t: Throwable) {
                callback.onFailure()
            }
        })
    }


    private fun getDefaultCategoryFromPreferences() = preferences.geDefaultCategory()

    fun getBooksFromDB(): List<Book> = repo.getAllFromDB()
}