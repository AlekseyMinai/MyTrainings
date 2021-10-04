package com.alexey.minay.feature_training.domain

data class TrainingSet(
    val id: TrainingSetId,
    val weight: Int,
    val count: Int
)