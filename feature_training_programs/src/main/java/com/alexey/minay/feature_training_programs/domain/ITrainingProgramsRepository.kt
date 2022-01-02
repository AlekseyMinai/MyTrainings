package com.alexey.minay.feature_training_programs.domain

import kotlinx.coroutines.flow.Flow

interface ITrainingProgramsRepository {
    fun getAllProgram(): Flow<List<TrainingProgram>>
    suspend fun createProgram(title: String)
}