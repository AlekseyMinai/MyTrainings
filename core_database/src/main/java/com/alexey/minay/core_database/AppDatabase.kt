package com.alexey.minay.core_database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.alexey.minay.core_database.training.TrainingListDao
import com.alexey.minay.core_database.training.entities.TrainingTypeDb

@Database(entities = [TrainingTypeDb::class], version = 1)
abstract class AppDatabase: RoomDatabase() {
    abstract fun getTrainingListDao(): TrainingListDao
}