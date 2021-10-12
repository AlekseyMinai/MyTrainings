package com.alexey.minay.feature_training.data

import com.alexey.minay.core_training.TrainingTypeId
import com.alexey.minay.feature_training.domain.*
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

    override suspend fun createSet(
        trainingId: TrainingId,
        exerciseId: ExerciseId,
        weight: Int,
        count: Int
    ) {
        withContext(Dispatchers.IO) {
            trainingStorage.addSet(trainingId, exerciseId, weight, count)
        }
    }

    override fun updateSet(setId: TrainingSetId, weight: Int, count: Int) {
        TODO("Not yet implemented")
    }

    override fun deleteSet(setId: TrainingSetId) {
        TODO("Not yet implemented")
    }

}