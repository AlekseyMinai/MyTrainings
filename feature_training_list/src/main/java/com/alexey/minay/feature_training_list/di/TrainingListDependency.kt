package com.alexey.minay.feature_training_list.di

import com.alexey.minay.core_database.training_list.TrainingListDao

interface TrainingListDependency {
    fun provideTrainingListDao(): TrainingListDao
}