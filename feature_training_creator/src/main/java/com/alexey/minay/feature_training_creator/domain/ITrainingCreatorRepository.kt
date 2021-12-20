package com.alexey.minay.feature_training_creator.domain

interface ITrainingCreatorRepository {
    suspend fun getExercises(): List<MuscleGroup>
    suspend fun saveTraining(training: Training)
}