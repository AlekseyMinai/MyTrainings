package com.alesno.mytrainings.navigation

import androidx.compose.runtime.Composable
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.alexey.minay.core_navigation.Destination
import com.alexey.minay.feature_training.presentation.TrainingStore
import com.alexey.minay.feature_training.view.TrainingScreen
import com.alexey.minay.feature_training_list.presentation.TrainingListStore
import com.alexey.minay.feature_training_list.view.TrainingListScreen

@Composable
fun NavGraph(
    navController: NavHostController,
    startDestination: Destination = Destination.TRAINING_LIST
) {
    val navigator = Navigator(navController)
    NavHost(
        navController = navController,
        startDestination = startDestination.value
    ) {
        composable(
            route = Destination.TRAINING_LIST.value,
        ) {
            val store = viewModel<TrainingListStore>(
                factory = TrainingListStore.provideFactory()
            )
            TrainingListScreen(
                store = store,
                navigator = navigator
            )
        }
        composable(
            route = Destination.TRAINING.value,
        ) {
            val store = viewModel<TrainingStore>(
                factory = TrainingStore.provideFactory()
            )
            TrainingScreen(
                store = store,
                navigator = navigator
            )
        }
    }
}