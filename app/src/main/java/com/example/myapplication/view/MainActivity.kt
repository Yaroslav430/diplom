package com.example.myapplication.view

import android.os.Bundle

import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.myapplication.view.Fragment.FavoritesFragment
import com.example.myapplication.view.Fragment.HomeFragment
import com.example.myapplication.R
import com.example.myapplication.data.Entity.Book
import com.example.myapplication.databinding.ActivityMainBinding
import com.example.myapplication.view.Fragment.DetailsFragment
import com.example.myapplication.view.Fragment.RecentlyFragment

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        initNavigation()


        supportFragmentManager
            .beginTransaction()
            .add(R.id.fragment_placeholder, HomeFragment())
            .addToBackStack(null)
            .commit()

    }

    fun launchDetailsFragment(book: Book) {
        val bundle = Bundle()
        bundle.putParcelable("book", book)
        val fragment = DetailsFragment()
        fragment.arguments = bundle

        supportFragmentManager
            .beginTransaction()
            .replace(R.id.fragment_placeholder, fragment)
            .addToBackStack(null)
            .commit()
    }


    private fun initNavigation() {

        binding.navView.setOnNavigationItemSelectedListener {

            when (it.itemId) {
                R.id.favorites -> {
                    supportFragmentManager
                        .beginTransaction()
                        .replace(R.id.fragment_placeholder, FavoritesFragment())
                        .addToBackStack(null)
                        .commit()
                    true
                }

                R.id.watch_later -> {
                    Toast.makeText(this, "Прочитать позже", Toast.LENGTH_SHORT).show()
                    true
                }

                R.id.selections -> {
                    Toast.makeText(this, "Подборки", Toast.LENGTH_SHORT).show()
                    true
                }
                R.id.recently -> {
                    supportFragmentManager
                        .beginTransaction()
                        .replace(R.id.fragment_placeholder, RecentlyFragment())
                        .addToBackStack(null)
                        .commit()
                    true
                }



                else -> false
        }
    }
    }
}
