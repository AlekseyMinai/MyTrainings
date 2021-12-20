package com.alexey.minay.feature_training_list.presentation

import androidx.lifecycle.viewModelScope
import com.alexey.minay.core_ui.SimpleStore
import com.alexey.minay.feature_training_list.domain.ITrainingListRepository
import kotlinx.coroutines.launch

class TrainingListStore(
    private val repository: ITrainingListRepository,
    initialState: TrainingListState
) : SimpleStore<TrainingListState, TrainingListIntent, TrainingListEvent>(initialState) {

    init {
        viewModelScope.launch {
            val result = repository.getTrainingList(state.value.programId)
            modify { copy(trainings = result) }
        }
    }

    override suspend fun execute(intent: TrainingListIntent) {

    }

}