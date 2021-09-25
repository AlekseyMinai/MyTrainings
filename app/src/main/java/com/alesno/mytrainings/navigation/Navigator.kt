package com.alesno.mytrainings.navigation

import androidx.navigation.NavController
import com.alexey.minay.core_navigation.Destination
import com.alexey.minay.core_navigation.INavigator
import javax.inject.Inject

class Navigator @Inject constructor(
    private val navController: NavController
) : INavigator {

    override fun navigateTo(destination: Destination) {
        navController.navigate(destination.value)
    }

    override fun popBackstack() {
        navController.popBackStack()
    }

}