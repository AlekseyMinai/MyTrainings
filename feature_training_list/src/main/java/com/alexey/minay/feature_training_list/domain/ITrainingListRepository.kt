package com.alexey.minay.feature_training_list.domain

interface ITrainingListRepository {
    fun getTrainingList(): List<TrainingInfo>
}