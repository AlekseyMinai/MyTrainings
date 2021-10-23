package com.alexey.minay.feature_training_history.domain

interface ITrainingHistoryRepository {
    suspend fun getTrainings(): List<Training>
}