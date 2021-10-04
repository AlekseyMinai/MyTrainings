package com.alexey.minay.feature_training.data

import com.alexey.minay.core_training.TrainingTypeId
import com.alexey.minay.feature_training.domain.Training
import com.alexey.minay.feature_training.domain.TrainingId
import kotlinx.coroutines.flow.Flow

interface ITrainingStorage {
    fun createTraining(trainingTypeId: TrainingTypeId): TrainingId
    fun getTrainingFlow(trainingId: TrainingId): Flow<Training>
}