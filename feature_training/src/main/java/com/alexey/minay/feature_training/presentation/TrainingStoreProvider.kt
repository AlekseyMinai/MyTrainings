package com.alexey.minay.feature_training.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import javax.inject.Inject


class TrainingStoreProvider @Inject constructor(
    private val trainingState: TrainingState
) : ViewModelProvider.Factory {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return TrainingStore(
            initialState = trainingState
        ) as T
    }

}