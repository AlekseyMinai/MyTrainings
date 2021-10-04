package com.alexey.minay.feature_training.data

import com.alexey.minay.core_training.TrainingTypeId
import com.alexey.minay.feature_training.domain.*
import kotlinx.coroutines.flow.Flow

class TrainingRepository(
    private val trainingStorage: ITrainingStorage
) : ITrainingRepository {

    override fun createTraining(trainingTypeId: TrainingTypeId): Flow<Training> {
        val trainingId = trainingStorage.createTraining(trainingTypeId)
        return trainingStorage.getTrainingFlow(trainingId)
    }

    override fun createSet(exerciseId: TrainingExerciseId, trainingSet: TrainingSet) {
        TODO("Not yet implemented")
    }

    override fun updateSet(
        exerciseId: TrainingExerciseId,
        setId: TrainingSetId,
        trainingSet: TrainingSet
    ) {
        TODO("Not yet implemented")
    }

    override fun deleteSet(exerciseId: TrainingExerciseId, setId: TrainingSetId) {
        TODO("Not yet implemented")
    }
}