package com.alesno.mytrainings.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.alexey.minay.core_navigation.Destination
import com.alexey.minay.feature_training.di.TrainingComponent
import com.alexey.minay.feature_training.presentation.TrainingStore
import com.alexey.minay.feature_training.view.TrainingScreen
import com.alexey.minay.feature_training_list.di.TrainingListComponent
import com.alexey.minay.feature_training_list.presentation.TrainingListStore
import com.alexey.minay.feature_training_list.view.TrainingListScreen

@Composable
fun NavGraph(
    navController: NavHostController,
    startDestination: Destination = Destination.TRAINING_LIST
) {
    NavHost(
        navController = navController,
        startDestination = startDestination.value
    ) {
        composable(
            route = Destination.TRAINING_LIST.value,
        ) {
            val trainingListComponent = TrainingListComponent.initAndGet()
            val store = viewModel<TrainingListStore>(
                factory = trainingListComponent.trainingListStoreProvider
            )
            TrainingListScreen(
                store = store,
                startTraining = { navController.navigate(Destination.TRAINING.value) }
            )
        }
        composable(
            route = Destination.TRAINING.value,
        ) {
            val trainingComponent = remember { TrainingComponent.initAndGet() }
            val store = viewModel<TrainingStore>(
                factory = trainingComponent.trainingStoreProvider
            )
            TrainingScreen(
                store = store,
                onBackPressed = {
                    navController.popBackStack()
                    TrainingComponent.release()
                }
            )
        }
    }
}