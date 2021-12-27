package com.alexey.minay.feature_training_list.presentation

import androidx.lifecycle.viewModelScope
import com.alexey.minay.core_ui.SimpleStore
import com.alexey.minay.feature_training_list.domain.ITrainingListRepository
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach

class TrainingListStore(
    repository: ITrainingListRepository,
    initialState: TrainingListState
) : SimpleStore<TrainingListState, TrainingListIntent, TrainingListEvent>(initialState) {

    init {
        repository.getTrainingList(state.value.programId)
            .onEach { modify { copy(trainings = it) } }
            .launchIn(viewModelScope)
    }

    override suspend fun execute(intent: TrainingListIntent) = Unit

}