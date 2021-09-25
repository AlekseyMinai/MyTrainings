package com.alexey.minay.feature_training_list.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.alexey.minay.core_ui.Store
import com.alexey.minay.feature_training_list.domain.GetTrainingListUseCase

class TrainingListStore(
    private val getTrainingListUseCase: GetTrainingListUseCase,
    initialState: TrainingListState
) : Store<TrainingListState, TrainingListIntent, TrainingListEvent>(initialState) {

    init {
        modify { copy(trainings = getTrainingListUseCase()) }
    }

    override suspend fun execute(intent: TrainingListIntent) {

    }

    companion object {

        fun provideFactory(
            initialState: TrainingListState = TrainingListState.default()
        ): ViewModelProvider.Factory = object : ViewModelProvider.Factory {

            override fun <T : ViewModel?> create(modelClass: Class<T>): T {
                return TrainingListStore(GetTrainingListUseCase(), initialState) as T
            }

        }

    }

}