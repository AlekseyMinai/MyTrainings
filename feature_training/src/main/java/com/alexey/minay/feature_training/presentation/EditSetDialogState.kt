package com.alexey.minay.feature_training.presentation

import com.alexey.minay.feature_training.domain.ExerciseId

data class EditSetDialogState(
    val exerciseId: ExerciseId,
    val isEditing: Boolean,
    val weight: String?,
    val count: String?
)
