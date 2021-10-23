package com.alexey.minay.core_database.training

import androidx.room.Dao
import androidx.room.Query

@Dao
interface ITrainingHistoryDao {
    @Query("SELECT * FROM Trainings")
    fun getAll(): List<TrainingWithType>
}