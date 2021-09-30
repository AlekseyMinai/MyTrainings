package com.alexey.minay.feature_training_list.data

import com.alexey.minay.core_database.training_list.TrainingListDao
import com.alexey.minay.core_database.training_list.TrainingTypeDb
import com.alexey.minay.feature_training_list.domain.ITrainingListRepository
import com.alexey.minay.feature_training_list.domain.TrainingType
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class TrainingListRepository @Inject constructor(
    private val trainingListDao: TrainingListDao
) : ITrainingListRepository {
    override suspend fun getTrainingList(): List<TrainingType> = withContext(Dispatchers.IO) {
        trainingListDao.getAll().map(TrainingTypeDb::toDomain)
    }
}