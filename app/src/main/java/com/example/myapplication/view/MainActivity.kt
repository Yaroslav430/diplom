package com.example.myapplication.view

import android.content.BroadcastReceiver
import android.content.Intent
import android.content.IntentFilter
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.example.myapplication.view.Fragment.FavoritesFragment
import com.example.myapplication.view.Fragment.HomeFragment
import com.example.myapplication.R
import com.example.myapplication.data.Entity.Book
import com.example.myapplication.databinding.ActivityMainBinding
import com.example.myapplication.receivers.ConnectionChecker
import com.example.myapplication.view.Fragment.DetailsFragment
import com.example.myapplication.view.Fragment.RecentlyFragment
import com.example.myapplication.view.Fragment.SettingsFragment

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var receiver: BroadcastReceiver

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

        receiver = ConnectionChecker()
        val filter = IntentFilter().apply {
            addAction(Intent.ACTION_POWER_CONNECTED)
            addAction(Intent.ACTION_BATTERY_LOW)
        }
           registerReceiver(receiver, filter)
    }

    override fun onDestroy() {
        super.onDestroy()
        unregisterReceiver(receiver)

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
        @Suppress("DEPRECATION")
        binding.navView.setOnNavigationItemSelectedListener {
            when (it.itemId) {
                R.id.home -> {
                    val tag = "home"
                    val fragment = checkFragmentExistence(tag)
                    changeFragment( fragment?: HomeFragment(), tag)
                    true
                }
                R.id.favorites -> {
                    val tag = "favorites"
                    val fragment = checkFragmentExistence(tag)
                    changeFragment( fragment?: FavoritesFragment(), tag)
                    true
                }
                R.id.settings_fragment_root -> {
                    val tag = "settings_fragment_root"
                    val fragment = checkFragmentExistence(tag)
                    changeFragment( fragment?: SettingsFragment(isNightModeOn = true), tag)
                    true
                }
                R.id.recently -> {
                    val tag = "recently"
                    val fragment = checkFragmentExistence(tag)
                    changeFragment(fragment?: RecentlyFragment(), tag)
                    true
                }
                else -> false
            }
        }
    }
    private fun checkFragmentExistence(tag: String): Fragment? = supportFragmentManager.findFragmentByTag(tag)

    private fun changeFragment(fragment: Fragment, tag: String) {
        supportFragmentManager
            .beginTransaction()
            .replace(R.id.fragment_placeholder, fragment, tag)
            .addToBackStack(null)
            .commit()
    }
}
