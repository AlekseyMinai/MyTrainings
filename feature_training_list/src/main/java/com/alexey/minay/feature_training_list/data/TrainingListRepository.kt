package com.alexey.minay.feature_training_list.data

import com.alexey.minay.core_database.training.ITrainingListDao
import com.alexey.minay.core_database.training.entities.TrainingTypeDb
import com.alexey.minay.core_training.TrainingProgramId
import com.alexey.minay.core_utils.CoroutineDispatchersProvider
import com.alexey.minay.feature_training_list.domain.ITrainingListRepository
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class TrainingListRepository @Inject constructor(
    private val trainingListDao: ITrainingListDao,
    private val dispatchers: CoroutineDispatchersProvider
) : ITrainingListRepository {

    override fun getTrainingList(programId: TrainingProgramId) =
        trainingListDao.getAllFor(programId.value).map { trainings ->
            trainings.map(TrainingTypeDb::toDomain)
        }

}