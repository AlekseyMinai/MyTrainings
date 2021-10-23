package com.alexey.minay.core_database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.alexey.minay.core_database.training.ITrainingDao
import com.alexey.minay.core_database.training.ITrainingHistoryDao
import com.alexey.minay.core_database.training.ITrainingListDao
import com.alexey.minay.core_database.training.entities.*

@Database(
    entities = [
        TrainingTypeDb::class,
        ExerciseDb::class,
        MuscleGroupDb::class,
        TrainingDb::class,
        ExerciseSetDb::class,
        TrainingTypeExercisesCrossRefDb::class,
        MuscleGroupExercisesCrossRefDb::class
    ],
    version = 1
)
abstract class AppDatabase : RoomDatabase() {
    abstract fun getTrainingListDao(): ITrainingListDao
    abstract fun getTrainingDao(): ITrainingDao
    abstract fun getTrainingHistoryDao(): ITrainingHistoryDao
}