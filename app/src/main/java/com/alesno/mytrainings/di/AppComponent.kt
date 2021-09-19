package com.alesno.mytrainings.di

import androidx.navigation.NavController
import com.alesno.mytrainings.navigation.Navigator

object AppComponent {

    lateinit var navigator: Navigator

    fun initNavigator(navController: NavController) {
        navigator = Navigator(navController = navController)
    }
}