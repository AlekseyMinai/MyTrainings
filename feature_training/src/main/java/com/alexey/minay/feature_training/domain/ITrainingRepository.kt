package com.alexey.minay.feature_training.domain

import com.alexey.minay.core_training.TrainingTypeId
import kotlinx.coroutines.flow.Flow

interface ITrainingRepository {
    suspend fun createTraining(trainingTypeId: TrainingTypeId): Flow<Training>
    suspend fun createSet(trainingId: TrainingId, exerciseId: ExerciseId, weight: Int, count: Int)
    fun updateSet(setId: TrainingSetId, weight: Int, count: Int)
    fun deleteSet(setId: TrainingSetId)
}