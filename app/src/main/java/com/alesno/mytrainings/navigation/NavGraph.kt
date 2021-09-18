package com.alesno.mytrainings.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.alesno.mytrainings.fake.FakeData
import com.alesno.mytrainings.presentation.training.TrainingState
import com.alesno.mytrainings.presentation.training.TrainingStore
import com.alesno.mytrainings.presentation.trainingList.TrainingListState
import com.alesno.mytrainings.presentation.trainingList.TrainingListStore
import com.alesno.mytrainings.ui.training.TrainingScreen
import com.alesno.mytrainings.ui.trainingList.TrainingListScreen
import com.alexey.minay.core_navigation.Destination

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
            TrainingListScreen(
                store = TrainingListStore(
                    initialState = TrainingListState(FakeData.getTrainings()),
                    navController = navController
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