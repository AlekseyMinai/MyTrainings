package com.alexey.minay.feature_training_programs.data

import com.alexey.minay.core_database.training.ITrainingProgramsDao
import com.alexey.minay.core_database.training.entities.TrainingProgramsDb
import com.alexey.minay.feature_training_programs.domain.ITrainingProgramsRepository
import com.alexey.minay.feature_training_programs.domain.TrainingProgram
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class TrainingProgramsRepository @Inject constructor(
    private val trainingProgramsDao: ITrainingProgramsDao
) : ITrainingProgramsRepository {

    override fun getAllProgram(): Flow<List<TrainingProgram>> {
        return trainingProgramsDao.getAllPrograms().map { it.map(TrainingProgramsDb::toDomain) }
    }

}
