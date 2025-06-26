package com.example.myapplication.di

import com.example.myapplication.viewmodel.HomeFragmentViewModel
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(
)
interface AppComponent {
    fun inject(homeFragmentViewModel: HomeFragmentViewModel)

}