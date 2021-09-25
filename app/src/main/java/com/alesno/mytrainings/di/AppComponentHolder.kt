package com.alesno.mytrainings.di

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.NavController

class AppComponentHolder(
    navController: NavController
) : ViewModel() {

    val appComponent: AppComponent = DaggerAppComponent.builder()
        .navController(navController)
        .build()

    companion object {

        fun provideFactory(navController: NavController): ViewModelProvider.Factory =
            object : ViewModelProvider.Factory {
                override fun <T : ViewModel?> create(modelClass: Class<T>): T {
                    return AppComponentHolder(navController) as T
                }
            }

    }

}