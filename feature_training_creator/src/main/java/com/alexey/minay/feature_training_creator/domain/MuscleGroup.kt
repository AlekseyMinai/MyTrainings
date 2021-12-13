package com.alexey.minay.feature_training_creator.domain

data class MuscleGroup(
    val muscleGroupId: MuscleGroupId,
    val title: String,
    val exercises: List<Exercise>
)