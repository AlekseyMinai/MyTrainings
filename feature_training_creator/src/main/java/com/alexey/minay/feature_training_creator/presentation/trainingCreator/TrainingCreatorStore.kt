package com.alexey.minay.feature_training_creator.presentation.trainingCreator

import com.alexey.minay.core_ui.Store
import com.alexey.minay.core_utils.exhaustive
import com.alexey.minay.feature_training_creator.domain.ITrainingCreatorRepository

class TrainingCreatorStore(
    initialState: TrainingCreatorState,
    private val repository: ITrainingCreatorRepository
) : Store<TrainingCreatorState, TrainingCreatorIntent, Nothing>(initialState) {

    override suspend fun execute(intent: TrainingCreatorIntent) {
        when (intent) {
            TrainingCreatorIntent.OpenExerciseSelectorScreen -> {
                modify { copy(type = TrainingCreatorState.Type.EXERCISE_SELECTOR) }
                val exercises = getExercises()
                modify { copy(exercises = exercises) }
            }
            TrainingCreatorIntent.OpenTrainingCreatorScreen ->
                modify { copy(type = TrainingCreatorState.Type.TRAINING_CREATOR) }
        }.exhaustive
    }

    private suspend fun getExercises(): List<TrainingCreatorState.Item> {
        val items = mutableListOf<TrainingCreatorState.Item>()
        repository.getExercises().forEach { muscleGroup ->
            items.add(
                TrainingCreatorState.MuscleGroupState(
                    title = muscleGroup.title,
                    isExpanded = true,
                )
            )

            muscleGroup.exercises.forEachIndexed { index, exercise ->
                items.add(
                    TrainingCreatorState.ExerciseState(
                        exercise = exercise,
                        isSelected = false,
                        isInExpandedGroup = true,
                        hasDivider = index != muscleGroup.exercises.lastIndex
                    )
                )
            }
        }

        return items
    }

}