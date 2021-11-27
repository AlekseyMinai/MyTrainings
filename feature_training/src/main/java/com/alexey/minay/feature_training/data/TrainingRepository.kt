package com.alexey.minay.feature_training.data

import com.alexey.minay.core_training.TrainingId
import com.alexey.minay.core_training.TrainingTypeId
import com.alexey.minay.core_training.ExerciseId
import com.alexey.minay.feature_training.domain.ITrainingRepository
import com.alexey.minay.feature_training.domain.Training
import com.alexey.minay.feature_training.domain.TrainingSetId
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.withContext
import javax.inject.Inject

class TrainingRepository @Inject constructor(
    private val trainingStorage: ITrainingStorage
) : ITrainingRepository {

    override suspend fun createTraining(trainingTypeId: TrainingTypeId): Flow<Training> {
        val trainingId = withContext(Dispatchers.IO) {
            trainingStorage.createTraining(trainingTypeId)
        }
        return trainingStorage.getTrainingFlow(trainingId)
    }

    override suspend fun getTraining(trainingId: TrainingId): Flow<Training> {
        return trainingStorage.getTrainingFlow(trainingId)
    }

    override suspend fun createSet(
        trainingId: TrainingId,
        exerciseId: ExerciseId,
        weight: Float,
        count: Int
    ) {
        withContext(Dispatchers.IO) {
            trainingStorage.addSet(trainingId, exerciseId, weight, count)
        }
    }

    override suspend fun updateSet(
        trainingId: TrainingId,
        setId: TrainingSetId,
        exerciseId: ExerciseId,
        weight: Float,
        count: Int
    ) {
        withContext(Dispatchers.IO) {
            trainingStorage.updateSet(
                trainingId = trainingId,
                setId = setId,
                exerciseId = exerciseId,
                weight = weight,
                count = count
            )
        }
    }

    override suspend fun deleteSet(setId: TrainingSetId) {
        withContext(Dispatchers.IO) {
            trainingStorage.deleteSet(setId)
        }
    }

}