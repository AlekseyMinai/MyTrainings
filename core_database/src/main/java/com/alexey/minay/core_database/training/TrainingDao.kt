package com.alexey.minay.core_database.training

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.alexey.minay.core_database.training.entities.TrainingDb
import kotlinx.coroutines.flow.Flow

@Dao
interface TrainingDao {
    @Insert
    fun insert(training: TrainingDb): Long

    @Query("SELECT * FROM Trainings WHERE trainingId")
    fun getById(trainingId: Long): Flow<TrainingWithExercises>
}