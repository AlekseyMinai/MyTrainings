package com.alexey.minay.feature_training.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.alexey.minay.feature_training.data.TrainingRepository
import javax.inject.Inject


class TrainingStoreFactory @Inject constructor(
    private val trainingState: TrainingState,
    private val repository: TrainingRepository
) : ViewModelProvider.Factory {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return TrainingStore(
            initialState = trainingState,
            repository = repository
        ) as T
    }

}