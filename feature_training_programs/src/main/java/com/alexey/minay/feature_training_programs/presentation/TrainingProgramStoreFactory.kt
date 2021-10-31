package com.alexey.minay.feature_training_programs.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.alexey.minay.feature_training_programs.domain.ITrainingProgramsRepository
import javax.inject.Inject

class TrainingProgramStoreFactory @Inject constructor(
    private val trainingProgramsRepository: ITrainingProgramsRepository,
    private val initialState: TrainingProgramsState
) : ViewModelProvider.Factory {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return TrainingProgramsStore(trainingProgramsRepository, initialState) as T
    }

}