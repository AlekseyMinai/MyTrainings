package com.alexey.minay.core_database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.alexey.minay.core_database.training.*
import com.alexey.minay.core_database.training.entities.*

@Database(
    entities = [
        TrainingTypeDb::class,
        ExerciseDb::class,
        MuscleGroupDb::class,
        TrainingDb::class,
        ExerciseSetDb::class,
        TrainingTypeExercisesCrossRefDb::class,
        MuscleGroupExercisesCrossRefDb::class,
        TrainingProgramDb::class,
        TrainingProgramsTrainingTypeCrossRefDb::class
    ],
    version = 1
)
abstract class AppDatabase : RoomDatabase() {
    abstract fun getTrainingListDao(): ITrainingListDao
    abstract fun getTrainingDao(): ITrainingDao
    abstract fun getTrainingHistoryDao(): ITrainingHistoryDao
    abstract fun getTrainingProgramsDao(): ITrainingProgramsDao
    abstract fun getExerciseGroupDao(): IExerciseGroupDao
}