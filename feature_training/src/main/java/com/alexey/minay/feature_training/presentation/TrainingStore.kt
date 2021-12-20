package com.alexey.minay.feature_training.presentation

import androidx.lifecycle.viewModelScope
import com.alexey.minay.core_ui.SimpleStore
import com.alexey.minay.core_utils.TrainingIdUtils
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
) : SimpleStore<TrainingState, TrainingIntent, TrainingEvent>(initialState) {

    init {
        viewModelScope.launch {
            when (state.value.training.trainingTypeId.value) {
                TrainingIdUtils.NO_ID -> repository.getTraining(initialState.training.id)
                else -> repository.createTraining(initialState.training.trainingTypeId)
            }.onEach(::update)
                .launchIn(this)
        }
    }

    override suspend fun execute(intent: TrainingIntent) {
        when (intent) {
            TrainingIntent.AddSet -> {
                if (state.value.editSetDialogState?.isEditing == false) {
                    repository.createSet(
                        trainingId = state.value.training.id,
                        exerciseId = state.value.editSetDialogState!!.exerciseId,
                        weight = state.value.editSetDialogState?.weight?.toFloatOrNull() ?: return,
                        count = state.value.editSetDialogState?.count?.toIntOrNull() ?: return
                    )
                } else {
                    repository.updateSet(
                        setId = state.value.editSetDialogState?.setId ?: return,
                        weight = state.value.editSetDialogState?.weight?.toFloatOrNull() ?: return,
                        count = state.value.editSetDialogState?.count?.toIntOrNull() ?: return,
                        exerciseId = state.value.editSetDialogState?.exerciseId ?: return,
                        trainingId = state.value.training.id
                    )
                }
                modify {
                    copy(
                        editSetDialogState = null,
                        type = TrainingState.Type.DEFAULT
                    )
                }
            }
            TrainingIntent.CancelAddingSet -> {
                modify {
                    copy(
                        editSetDialogState = null,
                        type = TrainingState.Type.DEFAULT
                    )
                }
            }
            is TrainingIntent.CreateSet -> modify {
                copy(
                    type = TrainingState.Type.EDIT_SET,
                    editSetDialogState = EditSetDialogState(
                        exerciseId = intent.exerciseId,
                        weight = null,
                        count = null,
                        isEditing = false,
                        setId = null
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
            is TrainingIntent.EditSet -> modify {
                copy(
                    type = TrainingState.Type.EDIT_SET,
                    editSetDialogState = EditSetDialogState(
                        exerciseId = intent.exerciseId,
                        weight = intent.trainingSet.weight.toString(),
                        count = intent.trainingSet.count.toString(),
                        isEditing = true,
                        setId = intent.trainingSet.id
                    )
                )
            }
            TrainingIntent.DeleteSet -> {
                repository.deleteSet(state.value.editSetDialogState?.setId ?: return)
                modify {
                    copy(
                        editSetDialogState = null,
                        type = TrainingState.Type.DEFAULT
                    )
                }
            }
        }.exhaustive
    }

    private fun update(training: Training) {
        modify { copy(training = training) }
    }

}