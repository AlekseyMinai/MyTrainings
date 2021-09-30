package com.alexey.minay.feature_training_list.presentation

import androidx.lifecycle.viewModelScope
import com.alexey.minay.core_ui.Store
import com.alexey.minay.feature_training_list.domain.ITrainingListRepository
import kotlinx.coroutines.launch

class TrainingListStore(
    private val repository: ITrainingListRepository,
    initialState: TrainingListState
) : Store<TrainingListState, TrainingListIntent, TrainingListEvent>(initialState) {

    init {
        viewModelScope.launch {
            val result = repository.getTrainingList()
            modify { copy(trainings = result) }
        }
    }

    override suspend fun execute(intent: TrainingListIntent) {

    }

}