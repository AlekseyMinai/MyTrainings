package com.alexey.minay.feature_training_list.domain

import com.alexey.minay.core_training.TrainingProgramId

interface ITrainingListRepository {
    suspend fun getTrainingList(programId: TrainingProgramId): List<TrainingType>
}