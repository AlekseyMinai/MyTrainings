package com.alexey.minay.feature_training_programs.data

import com.alexey.minay.core_database.training.ITrainingProgramsDao
import com.alexey.minay.core_database.training.entities.TrainingProgramDb
import com.alexey.minay.core_utils.CoroutineDispatchersProvider
import com.alexey.minay.feature_training_programs.domain.ITrainingProgramsRepository
import com.alexey.minay.feature_training_programs.domain.TrainingProgram
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.withContext
import javax.inject.Inject

class TrainingProgramsRepository @Inject constructor(
    private val trainingProgramsDao: ITrainingProgramsDao,
    private val dispatchersProvider: CoroutineDispatchersProvider
) : ITrainingProgramsRepository {

    override fun getAllProgram(): Flow<List<TrainingProgram>> {
        return trainingProgramsDao.getAllPrograms().map { it.map(TrainingProgramDb::toDomain) }
    }

    override suspend fun createProgram(title: String) = withContext(dispatchersProvider.io) {
        trainingProgramsDao.insertTrainingProgram(TrainingProgramDb(title))
    }

}
