package com.alexey.minay.feature_training_list.presentation

import com.alexey.minay.core_ui.Store

class TrainingListStore(
    //private val navController: NavController,
    initialState: TrainingListState
) : Store<TrainingListState, TrainingListIntent, TrainingListEvent>(initialState) {

    override suspend fun execute(intent: TrainingListIntent) {
        when(intent) {
            is TrainingListIntent.StartNewTraining -> Unit //navController.navigate(Destination.TRAINING.value)
        }
    }

}