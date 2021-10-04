package com.alexey.minay.feature_training.data.trainingStorage

import com.alexey.minay.core_database.training.TrainingDao
import com.alexey.minay.core_database.training.entities.TrainingDb
import com.alexey.minay.core_training.TrainingTypeId
import com.alexey.minay.feature_training.data.ITrainingStorage
import com.alexey.minay.feature_training.domain.Training
import com.alexey.minay.feature_training.domain.TrainingId
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import java.util.*

class TrainingStorage(
    private val trainingDao: TrainingDao
) : ITrainingStorage {

    override fun createTraining(trainingTypeId: TrainingTypeId): TrainingId {
        val trainingDb = TrainingDb(
            date = Date(), // FIXME: 01.10.2021 Провайдить снаружи
            trainingTypeId = trainingTypeId.value
        )
        return TrainingId(trainingDao.insert(trainingDb))
    }

    override fun getTrainingFlow(trainingId: TrainingId): Flow<Training> {
        return trainingDao.getById(trainingId.value).map { it.toDomain() }
    }
}