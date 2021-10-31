package com.alexey.minay.feature_training_programs.domain

import com.alexey.minay.core_database.training.ITrainingProgramsDao

interface ITrainingProgramsDependencies {
    fun provideTrainingProgramsDao(): ITrainingProgramsDao
}