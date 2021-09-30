package com.alexey.minay.feature_training_list.domain

interface ITrainingListRepository {
    suspend fun getTrainingList(): List<TrainingType>
}