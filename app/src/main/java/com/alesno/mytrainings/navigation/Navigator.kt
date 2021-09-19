package com.alesno.mytrainings.navigation

import androidx.navigation.NavController
import com.alexey.minay.core_navigation.Destination
import com.alexey.minay.core_navigation.INavigator

class Navigator(
    private val navController: NavController
): INavigator {

    override fun navigateTo(destination: Destination) {
        navController.navigate(destination.value)
    }

}