package com.alexey.minay.feature_training_list.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.alexey.minay.feature_training_list.domain.ITrainingListRepository
import javax.inject.Inject

class TrainingListStoreProvider @Inject constructor(
    private val trainingListRepository: ITrainingListRepository,
    private val initialState: TrainingListState
) : ViewModelProvider.Factory {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return TrainingListStore(trainingListRepository, initialState) as T
    }

}