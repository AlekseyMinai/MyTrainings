package com.alexey.minay.feature_training.presentation

import androidx.lifecycle.viewModelScope
import com.alexey.minay.core_ui.Store
import com.alexey.minay.core_utils.exhaustive
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
            is TrainingIntent.AddSet -> {
                repository.createSet(
                    trainingId = state.value.training.id,
                    exerciseId = state.value.editSetDialogState!!.exerciseId,
                    weight = state.value.editSetDialogState?.weight!!,
                    count = state.value.editSetDialogState?.count!!
                )
                modify { copy(type = TrainingState.Type.DEFAULT) }
            }
            is TrainingIntent.OpenEditSetDialog -> modify {
                copy(
                    type = TrainingState.Type.EDIT_SET,
                    editSetDialogState = EditSetDialogState(
                        exerciseId = intent.exerciseId,
                        weight = null,
                        count = null,
                        isEditing = false
                    )
                )
            }
            is TrainingIntent.ChangeCount -> modify {
                copy(
                    editSetDialogState = editSetDialogState?.copy(
                        count = intent.count
                    )
                )
            }
            is TrainingIntent.ChangeWeight -> modify {
                copy(
                    editSetDialogState = editSetDialogState?.copy(
                        weight = intent.weight
                    )
                )
            }
        }.exhaustive
    }

    private fun update(training: Training) {
        modify { copy(training = training) }
    }

}