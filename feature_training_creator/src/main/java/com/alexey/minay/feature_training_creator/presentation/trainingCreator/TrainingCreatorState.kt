package com.alexey.minay.feature_training_creator.presentation.trainingCreator

import com.alexey.minay.feature_training_creator.domain.Exercise
import com.alexey.minay.feature_training_creator.domain.MuscleGroupId

data class TrainingCreatorState(
    val title: String,
    val items: List<Item>,
    val type: Type
) {

    sealed interface Item

    data class MuscleGroupState(
        val title: String,
        val isExpanded: Boolean,
        val muscleGroupId: MuscleGroupId
    ) : Item

    data class ExerciseState(
        val exercise: Exercise,
        val isInExpandedGroup: Boolean,
        val isSelected: Boolean,
        val hasDivider: Boolean,
        val muscleGroupId: MuscleGroupId
    ) : Item

    enum class Type {
        TRAINING_CREATOR,
        EXERCISE_SELECTOR
    }

    companion object {

        fun default() = TrainingCreatorState(
            title = "",
            items = emptyList(),
            type = Type.TRAINING_CREATOR
        )

    }

}