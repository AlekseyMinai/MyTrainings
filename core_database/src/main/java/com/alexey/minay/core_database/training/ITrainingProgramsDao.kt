package com.alexey.minay.core_database.training

import androidx.room.Dao
import androidx.room.Query
import com.alexey.minay.core_database.training.entities.TrainingProgramsDb
import kotlinx.coroutines.flow.Flow

@Dao
interface ITrainingProgramsDao {

    @Query("SELECT * FROM TrainingPrograms")
    fun getAllPrograms(): Flow<List<TrainingProgramsDb>>
}