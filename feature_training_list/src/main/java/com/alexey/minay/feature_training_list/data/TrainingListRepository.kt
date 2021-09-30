package com.alexey.minay.feature_training_list.data

import com.alexey.minay.core_database.training_list.TrainingListDao
import com.alexey.minay.core_database.training_list.TrainingTypeDb
import com.alexey.minay.core_utils.CoroutineDispatchersProvider
import com.alexey.minay.feature_training_list.domain.ITrainingListRepository
import kotlinx.coroutines.withContext
import javax.inject.Inject

class TrainingListRepository @Inject constructor(
    private val trainingListDao: TrainingListDao,
    private val dispatchers: CoroutineDispatchersProvider
) : ITrainingListRepository {

    override suspend fun getTrainingList() = withContext(dispatchers.io) {
        trainingListDao.getAll().map(TrainingTypeDb::toDomain)
    }

}