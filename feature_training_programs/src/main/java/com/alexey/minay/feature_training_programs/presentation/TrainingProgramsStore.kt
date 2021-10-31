package com.alexey.minay.feature_training_programs.presentation

import androidx.lifecycle.viewModelScope
import com.alexey.minay.core_ui.Store
import com.alexey.minay.feature_training_programs.domain.ITrainingProgramsRepository
import com.alexey.minay.feature_training_programs.domain.TrainingProgram
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach

class TrainingProgramsStore(
    repository: ITrainingProgramsRepository,
    initialValue: TrainingProgramsState
) : Store<TrainingProgramsState, ITrainingProgramsIntent, ITrainingProgramsEvent>(initialValue) {

    init {
        repository.getAllProgram()
            .onEach(::handleResult)
            .launchIn(viewModelScope)
    }

    override suspend fun execute(intent: ITrainingProgramsIntent) {

    }

    private fun handleResult(trainingPrograms: List<TrainingProgram>) {
        modify {
            copy(programs = trainingPrograms)
        }
    }

}