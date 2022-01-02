package com.alexey.minay.feature_training_programs.presentation

import androidx.lifecycle.viewModelScope
import com.alexey.minay.core_ui.SimpleStore
import com.alexey.minay.core_utils.exhaustive
import com.alexey.minay.feature_training_programs.domain.ITrainingProgramsRepository
import com.alexey.minay.feature_training_programs.domain.TrainingProgram
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch

class TrainingProgramsStore(
    private val repository: ITrainingProgramsRepository,
    initialValue: TrainingProgramsState
) : SimpleStore<TrainingProgramsState, ITrainingProgramsIntent, ITrainingProgramsEvent>(initialValue) {

    init {
        repository.getAllProgram()
            .onEach(::handleResult)
            .launchIn(viewModelScope)
    }

    override suspend fun execute(intent: ITrainingProgramsIntent) {
        when (intent) {
            is ITrainingProgramsIntent.ChangeNewTrainingProgramTitle ->
                changeNewTrainingProgramTitle(intent.title)
            ITrainingProgramsIntent.CreateTrainingProgram ->
                createTrainingProgram()
            ITrainingProgramsIntent.ConfirmTrainingCreation -> confirmTrainingCreation()
            ITrainingProgramsIntent.DismissTrainingCreation -> dismissTrainingCreation()
        }.exhaustive
    }

    private fun changeNewTrainingProgramTitle(title: String) {
        modify {
            copy(
                trainingProgramCreationState = TrainingProgramsState
                    .TrainingProgramCreationState(title)
            )
        }
    }

    private fun createTrainingProgram() {
        modify {
            copy(
                trainingProgramCreationState = TrainingProgramsState
                    .TrainingProgramCreationState(""),
                type = TrainingProgramsState.Type.CREATING_PROGRAM
            )
        }
    }

    private fun confirmTrainingCreation() {
        val title = getState().trainingProgramCreationState?.title ?: return
        viewModelScope.launch {
            repository.createProgram(title)
        }
        dismissTrainingCreation()
    }


    private fun dismissTrainingCreation() {
        modify {
            copy(
                trainingProgramCreationState = null,
                type = TrainingProgramsState.Type.PROGRAMS
            )
        }
    }

    private fun handleResult(trainingPrograms: List<TrainingProgram>) {
        modify {
            copy(programs = trainingPrograms)
        }
    }

}