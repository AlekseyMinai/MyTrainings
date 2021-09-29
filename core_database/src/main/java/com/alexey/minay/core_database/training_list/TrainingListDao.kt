package com.alexey.minay.core_database.training_list

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query

@Dao
interface TrainingListDao {
    @Insert
    fun insert(trainingInoList: List<TrainingTypeDb>)

    @Query("SELECT * FROM TrainingTypeDb")
    fun getAll(): List<TrainingTypeDb>
}