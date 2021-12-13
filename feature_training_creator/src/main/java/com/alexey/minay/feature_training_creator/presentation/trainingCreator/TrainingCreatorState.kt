package com.alexey.minay.feature_training_creator.presentation.trainingCreator

import com.alexey.minay.feature_training_creator.domain.Exercise
import com.alexey.minay.feature_training_creator.domain.MuscleGroup

data class TrainingCreatorState(
    val title: String,
    val exercises: List<Item>,
    val type: Type
) {

    sealed interface Item

    data class MuscleGroupState(
        val title: String,
        val isExpanded: Boolean
    ) : Item

    data class ExerciseState(
        val exercise: Exercise,
        val isInExpandedGroup: Boolean,
        val isSelected: Boolean,
        val hasDivider: Boolean
    ) : Item

    enum class Type {
        TRAINING_CREATOR,
        EXERCISE_SELECTOR
    }

    companion object {

        fun default() = TrainingCreatorState(
            title = "",
            exercises = emptyList(),
            type = Type.TRAINING_CREATOR
        )

    }

}