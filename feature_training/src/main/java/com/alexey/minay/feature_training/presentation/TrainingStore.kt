package com.alexey.minay.feature_training.presentation

import com.alexey.minay.core_ui.Store

class TrainingStore(
    initialState: TrainingState
) : Store<TrainingState, TrainingIntent, TrainingEvent>(initialState) {

    override suspend fun execute(intent: TrainingIntent) {
        when (intent) {
            is TrainingIntent.AddSet -> {

            }
        }
    }

}