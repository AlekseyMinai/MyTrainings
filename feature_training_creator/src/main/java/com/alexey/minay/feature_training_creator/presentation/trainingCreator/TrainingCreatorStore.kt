package com.alexey.minay.feature_training_creator.presentation.trainingCreator

import com.alexey.minay.core_ui.Store
import com.alexey.minay.feature_training_creator.domain.ITrainingCreatorRepository

class TrainingCreatorStore(
    initialState: TrainingCreatorState,
    private val repository: ITrainingCreatorRepository
) : Store<TrainingCreatorState, TrainingCreatorIntent, Nothing>(initialState) {

    override suspend fun execute(intent: TrainingCreatorIntent) {

    }

}