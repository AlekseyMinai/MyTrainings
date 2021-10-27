package com.alexey.minay.feature_training.domain

import com.alexey.minay.core_training.TrainingId
import com.alexey.minay.core_training.TrainingTypeId
import kotlinx.coroutines.flow.Flow

interface ITrainingRepository {
    suspend fun createTraining(trainingTypeId: TrainingTypeId): Flow<Training>
    suspend fun getTraining(trainingId: TrainingId): Flow<Training>
    suspend fun createSet(
        trainingId: TrainingId,
        exerciseId: ExerciseId,
        weight: Float,
        count: Int
    )

    suspend fun updateSet(
        trainingId: TrainingId,
        setId: TrainingSetId,
        exerciseId: ExerciseId,
        weight: Float,
        count: Int
    )

    fun deleteSet(setId: TrainingSetId)
}