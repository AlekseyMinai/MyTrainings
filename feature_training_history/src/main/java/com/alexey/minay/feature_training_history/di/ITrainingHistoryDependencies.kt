package com.alexey.minay.feature_training_history.di

import com.alexey.minay.core_database.training.ITrainingHistoryDao

interface ITrainingHistoryDependencies {
    fun provideTrainingHistoryDao(): ITrainingHistoryDao
}