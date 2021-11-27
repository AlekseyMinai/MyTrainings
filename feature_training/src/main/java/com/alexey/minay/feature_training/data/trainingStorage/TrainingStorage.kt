package com.alexey.minay.feature_training.data.trainingStorage

import com.alexey.minay.core_database.training.ITrainingDao
import com.alexey.minay.core_database.training.entities.ExerciseSetDb
import com.alexey.minay.core_database.training.entities.TrainingDb
import com.alexey.minay.core_training.TrainingId
import com.alexey.minay.core_training.TrainingTypeId
import com.alexey.minay.feature_training.data.ITrainingStorage
import com.alexey.minay.core_training.ExerciseId
import com.alexey.minay.feature_training.domain.Training
import com.alexey.minay.feature_training.domain.TrainingSetId
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import java.util.*
import javax.inject.Inject

class TrainingStorage @Inject constructor(
    private val trainingDao: ITrainingDao
) : ITrainingStorage {

    override fun createTraining(trainingTypeId: TrainingTypeId): TrainingId {
        val trainingDb = TrainingDb(
            date = Date().time, // FIXME: 01.10.2021 Провайдить снаружи
            trainingTypeId = trainingTypeId.value
        )
        return TrainingId(trainingDao.insertTraining(trainingDb))
    }

    override fun getTrainingFlow(trainingId: TrainingId): Flow<Training> {
        return trainingDao.getById(trainingId.value).map { it.toDomain() }
    }

    override fun addSet(trainingId: TrainingId, exerciseId: ExerciseId, weight: Float, count: Int) {
        val setDb = ExerciseSetDb(
            weight = weight,
            count = count,
            trainingId = trainingId.value,
            exerciseId = exerciseId.value
        )
        trainingDao.insertSet(setDb)
    }

    override fun updateSet(
        trainingId: TrainingId,
        setId: TrainingSetId,
        exerciseId: ExerciseId,
        weight: Float,
        count: Int
    ) {
        val set = ExerciseSetDb(
            weight = weight,
            count = count,
            trainingId = trainingId.value,
            exerciseId = exerciseId.value
        )
        set.setId = setId.value
        trainingDao.updateSet(set)
    }

    override fun deleteSet(setId: TrainingSetId) {
        trainingDao.deleteSet(setId.value)
    }

}