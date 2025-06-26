package com.example.myapplication

import android.app.Application
import com.example.myapplication.di.AppComponent


class App : Application() {
    lateinit var dagger: AppComponent

    override fun onCreate() {
        super.onCreate()
        instance = this

        dagger = DaggerAppComponent.builder()
            .build()
    }

    companion object {
        lateinit var instance: App
            private set
    }
}
