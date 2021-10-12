package com.alexey.minay.feature_training.presentation

import androidx.lifecycle.viewModelScope
import com.alexey.minay.core_ui.Store
import com.alexey.minay.feature_training.domain.ITrainingRepository
import com.alexey.minay.feature_training.domain.Training
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject

class TrainingStore @Inject constructor(
    private val repository: ITrainingRepository,
    initialState: TrainingState
) : Store<TrainingState, TrainingIntent, TrainingEvent>(initialState) {

    init {
        viewModelScope.launch {
            repository.createTraining(initialState.training.trainingTypeId)
                .onEach(::update)
                .launchIn(this)
        }
    }

    override suspend fun execute(intent: TrainingIntent) {
        when (intent) {
            is TrainingIntent.AddSet -> repository.createSet(
                trainingId = state.value.training.id,
                exerciseId = intent.exerciseId,
                weight = intent.weight,
                count = intent.count
            )
        }
    }

    private fun update(training: Training) {
        modify { copy(training = training) }
    }

}