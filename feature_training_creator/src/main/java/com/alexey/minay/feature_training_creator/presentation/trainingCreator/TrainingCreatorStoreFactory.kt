package com.alexey.minay.feature_training_creator.presentation.trainingCreator

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.alexey.minay.feature_training_creator.domain.ITrainingCreatorRepository
import javax.inject.Inject

class TrainingCreatorStoreFactory @Inject constructor(
    private val initialState: TrainingCreatorState,
    private val repository: ITrainingCreatorRepository
) : ViewModelProvider.Factory {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return TrainingCreatorStore(
            initialState = initialState,
            repository = repository
        ) as T
    }

}