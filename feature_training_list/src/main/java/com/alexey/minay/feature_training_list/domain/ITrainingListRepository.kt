package com.alexey.minay.feature_training_list.domain

import com.alexey.minay.core_training.TrainingProgramId
import kotlinx.coroutines.flow.Flow

interface ITrainingListRepository {
    fun getTrainingList(programId: TrainingProgramId): Flow<List<TrainingType>>
}