package com.alesno.mytrainings.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.navigation

@Composable
fun NavGraph(
    navController: NavHostController,
    startDestination: Destination
) {
    NavHost(navController = navController, startDestination = startDestination.value) {
//        navigation(
//            route = Destination.MAIN
//        )
    }
}