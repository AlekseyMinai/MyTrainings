package com.alexey.minay.feature_training_history.data

import com.alexey.minay.core_database.training.ITrainingHistoryDao
import com.alexey.minay.core_database.training.TrainingWithType
import com.alexey.minay.feature_training_history.domain.ITrainingHistoryRepository
import com.alexey.minay.feature_training_history.domain.Training
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class TrainingHistoryRepository @Inject constructor(
    private val trainingHistoryDao: ITrainingHistoryDao
) : ITrainingHistoryRepository {

    override suspend fun getTrainings(): List<Training> = withContext(Dispatchers.IO) {
        trainingHistoryDao.getAll().map(TrainingWithType::toDomain)
    }

}