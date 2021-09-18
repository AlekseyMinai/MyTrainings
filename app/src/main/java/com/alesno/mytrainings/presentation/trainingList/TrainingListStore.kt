package com.alesno.mytrainings.presentation.trainingList

import androidx.navigation.NavController
import com.alexey.minay.core_ui.Store
import com.alexey.minay.core_navigation.Destination

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