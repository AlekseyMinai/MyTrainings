package com.alesno.mytrainings.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.alesno.mytrainings.fake.FakeData
import com.alexey.minay.feature_training.presentation.TrainingState
import com.alexey.minay.feature_training.presentation.TrainingStore
import com.alexey.minay.feature_training_list.presentation.TrainingListState
import com.alexey.minay.feature_training_list.presentation.TrainingListStore
import com.alexey.minay.feature_training.view.TrainingScreen
import com.alexey.minay.feature_training_list.view.TrainingListScreen
import com.alexey.minay.core_navigation.Destination
import com.alexey.minay.core_navigation.INavigator

@Composable
fun NavGraph(
    navController: NavHostController,
    startDestination: Destination = Destination.TRAINING_LIST,
    navigator: INavigator
) {
    NavHost(
        navController = navController,
        startDestination = startDestination.value
    ) {
        composable(
            route = Destination.TRAINING_LIST.value,
        ) {
            TrainingListScreen(
                store = TrainingListStore(
                    initialState = TrainingListState(FakeData.getTrainings()),
                    navigator = navigator
                )
            )
        }
        composable(
            route = Destination.TRAINING.value,
        ) {
            TrainingScreen(store = TrainingStore(TrainingState(FakeData.createTraining())))
        }
    }
}