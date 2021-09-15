package com.alesno.mytrainings.presentation.trainingList

import com.alesno.mytrainings.lib.Store

class TrainingListStore(
    initialState: TrainingListState
) : Store<TrainingListState, TrainingListIntent, TrainingListEvent>(initialState) {

    override suspend fun execute(intent: TrainingListIntent) {
        TODO("Not yet implemented")
    }

}