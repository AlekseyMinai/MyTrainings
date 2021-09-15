package com.alesno.mytrainings.presentation.training

import com.alesno.mytrainings.lib.Store

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