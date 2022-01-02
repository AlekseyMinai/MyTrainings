package com.alexey.minay.core_database.training

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.alexey.minay.core_database.training.entities.TrainingProgramDb
import kotlinx.coroutines.flow.Flow

@Dao
interface ITrainingProgramsDao {

    @Query("SELECT * FROM TrainingPrograms")
    fun getAllPrograms(): Flow<List<TrainingProgramDb>>

    @Insert
    fun insertTrainingProgram(trainingProgramDb: TrainingProgramDb)

}