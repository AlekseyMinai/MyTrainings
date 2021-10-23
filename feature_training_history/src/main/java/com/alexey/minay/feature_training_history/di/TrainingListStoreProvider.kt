package com.alexey.minay.feature_training_history.di

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.alexey.minay.feature_training_history.domain.ITrainingHistoryRepository
import com.alexey.minay.feature_training_history.presentation.TrainingHistoryState
import com.alexey.minay.feature_training_history.presentation.TrainingHistoryStore
import javax.inject.Inject

class TrainingHistoryStoreFactory @Inject constructor(
    private val repository: ITrainingHistoryRepository,
    private val initialState: TrainingHistoryState
) : ViewModelProvider.Factory {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return TrainingHistoryStore(
            trainingHistoryRepository = repository,
            initialState = initialState
        ) as T
    }

}