package com.alexey.minay.feature_training_history.presentation

import androidx.lifecycle.viewModelScope
import com.alexey.minay.core_ui.SimpleStore
import com.alexey.minay.feature_training_history.domain.ITrainingHistoryRepository
import kotlinx.coroutines.launch

class TrainingHistoryStore(
    private val trainingHistoryRepository: ITrainingHistoryRepository,
    initialState: TrainingHistoryState
) : SimpleStore<TrainingHistoryState, TrainingHistoryIntent, TrainingHistoryEvent>(initialState) {

    init {
        viewModelScope.launch {
            val trainings = trainingHistoryRepository.getTrainings()
            modify { copy(trainings = trainings) }
        }
    }

    override suspend fun execute(intent: TrainingHistoryIntent) {

    }

}