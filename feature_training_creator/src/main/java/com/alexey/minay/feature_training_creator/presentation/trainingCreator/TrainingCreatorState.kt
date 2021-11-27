package com.alexey.minay.feature_training_creator.presentation.trainingCreator

import com.alexey.minay.feature_training_creator.domain.Exercise

data class TrainingCreatorState(
    val title: String,
    val exercises: List<Exercise>
) {

    companion object {

        fun default() = TrainingCreatorState(
            title = "",
            exercises = emptyList()
        )

    }

}