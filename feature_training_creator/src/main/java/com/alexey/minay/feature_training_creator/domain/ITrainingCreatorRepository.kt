package com.alexey.minay.feature_training_creator.domain

interface ITrainingCreatorRepository {
    fun getExercises(): List<Exercise>
}