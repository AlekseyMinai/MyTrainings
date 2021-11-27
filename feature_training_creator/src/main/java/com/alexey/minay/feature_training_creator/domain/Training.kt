package com.alexey.minay.feature_training_creator.domain

data class Training(
    val title: String,
    val exercises: List<Exercise>
)