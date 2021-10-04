package com.alexey.minay.feature_training.domain

import com.alexey.minay.core_training.TrainingTypeId
import kotlinx.coroutines.flow.Flow

interface ITrainingRepository {
    fun createTraining(trainingTypeId: TrainingTypeId): Flow<Training>
    fun createSet(exerciseId: TrainingExerciseId, trainingSet: TrainingSet)
    fun updateSet(exerciseId: TrainingExerciseId, setId: TrainingSetId, trainingSet: TrainingSet)
    fun deleteSet(exerciseId: TrainingExerciseId, setId: TrainingSetId)
}