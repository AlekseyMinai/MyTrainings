package com.alesno.mytrainings.presentation.trainingList

import androidx.navigation.NavController
import com.alesno.mytrainings.lib.Store
import com.alesno.mytrainings.navigation.Destination

class TrainingListStore(
    private val navController: NavController,
    initialState: TrainingListState
) : Store<TrainingListState, TrainingListIntent, TrainingListEvent>(initialState) {

    override suspend fun execute(intent: TrainingListIntent) {
        when(intent) {
            is TrainingListIntent.StartNewTraining -> navController.navigate(Destination.TRAINING.value)
        }
    }

}