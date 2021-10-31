package com.alexey.minay.feature_training_list.data

import com.alexey.minay.core_database.training.ITrainingListDao
import com.alexey.minay.core_database.training.entities.TrainingTypeDb
import com.alexey.minay.core_training.TrainingProgramId
import com.alexey.minay.core_utils.CoroutineDispatchersProvider
import com.alexey.minay.feature_training_list.domain.ITrainingListRepository
import kotlinx.coroutines.withContext
import javax.inject.Inject

class TrainingListRepository @Inject constructor(
    private val trainingListDao: ITrainingListDao,
    private val dispatchers: CoroutineDispatchersProvider
) : ITrainingListRepository {

    override suspend fun getTrainingList(programId: TrainingProgramId) = withContext(dispatchers.io) {
        trainingListDao.getAllFor(programId.value).map(TrainingTypeDb::toDomain)
    }

}