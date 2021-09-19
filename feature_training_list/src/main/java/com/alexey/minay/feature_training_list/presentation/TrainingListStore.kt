package com.alexey.minay.feature_training_list.presentation

import com.alexey.minay.core_navigation.Destination
import com.alexey.minay.core_navigation.INavigator
import com.alexey.minay.core_ui.Store

class TrainingListStore(
    private val navigator: INavigator,
    initialState: TrainingListState
) : Store<TrainingListState, TrainingListIntent, TrainingListEvent>(initialState) {

    override suspend fun execute(intent: TrainingListIntent) {
        when(intent) {
            is TrainingListIntent.StartNewTraining -> navigator.navigateTo(Destination.TRAINING)
        }
    }

}