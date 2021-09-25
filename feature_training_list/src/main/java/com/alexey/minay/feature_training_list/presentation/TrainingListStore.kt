package com.alexey.minay.feature_training_list.presentation

import com.alexey.minay.core_ui.Store
import com.alexey.minay.feature_training_list.domain.ITrainingListRepository

class TrainingListStore(
    private val repository: ITrainingListRepository,
    initialState: TrainingListState
) : Store<TrainingListState, TrainingListIntent, TrainingListEvent>(initialState) {

    init {
        modify { copy(trainings = repository.getTrainingList()) }
    }

    override suspend fun execute(intent: TrainingListIntent) {

    }

}