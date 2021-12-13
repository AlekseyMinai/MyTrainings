package com.alexey.minay.core_database.training

import androidx.room.Dao
import androidx.room.Query

@Dao
interface IExerciseGroupDao {
    @Query("SELECT * FROM MuscleGroup")
    fun getExerciseGroup(): List<MuscleGroupWithExercises>
}