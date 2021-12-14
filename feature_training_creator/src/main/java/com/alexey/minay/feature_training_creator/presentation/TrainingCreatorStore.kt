package com.alexey.minay.feature_training_creator.presentation

import com.alexey.minay.core_training.ExerciseId
import com.alexey.minay.core_ui.Store
import com.alexey.minay.core_utils.exhaustive
import com.alexey.minay.feature_training_creator.domain.ITrainingCreatorRepository
import com.alexey.minay.feature_training_creator.domain.MuscleGroupId

class TrainingCreatorStore(
    initialState: TrainingCreatorState,
    private val repository: ITrainingCreatorRepository
) : Store<TrainingCreatorState, TrainingCreatorAction, Nothing>(initialState) {

    override suspend fun execute(intent: TrainingCreatorAction) {
        when (intent) {
            TrainingCreatorAction.OpenExerciseSelectorScreen ->
                openExerciseSelectorScreen()
            TrainingCreatorAction.OpenTrainingCreatorScreen ->
                modify { copy(type = TrainingCreatorState.Type.TRAINING_CREATOR) }
            is TrainingCreatorAction.ChangeExerciseSelection ->
                changeExerciseSelection(intent.exerciseId)
            is TrainingCreatorAction.ChangeExpandState ->
                changeExpandState(intent.muscleGroupId)
        }.exhaustive
    }

    private suspend fun openExerciseSelectorScreen() {
        modify { copy(type = TrainingCreatorState.Type.EXERCISE_SELECTOR) }

        if (state.value.items.isNotEmpty()) {
            return
        }

        val exercises = getExercises()
        modify { copy(items = exercises) }
    }

    private suspend fun getExercises(): List<TrainingCreatorState.Item> {
        val items = mutableListOf<TrainingCreatorState.Item>()
        repository.getExercises().forEach { muscleGroup ->
            items.add(
                TrainingCreatorState.MuscleGroupState(
                    title = muscleGroup.title,
                    isExpanded = true,
                    muscleGroupId = muscleGroup.muscleGroupId
                )
            )

            muscleGroup.exercises.forEachIndexed { index, exercise ->
                items.add(
                    TrainingCreatorState.ExerciseState(
                        exercise = exercise,
                        isSelected = false,
                        isInExpandedGroup = true,
                        hasDivider = index != muscleGroup.exercises.lastIndex,
                        muscleGroupId = muscleGroup.muscleGroupId
                    )
                )
            }
        }

        return items
    }

    private fun changeExerciseSelection(exerciseId: ExerciseId) {
        modify {
            copy(
                items = items.map { item ->
                    when (item) {
                        is TrainingCreatorState.ExerciseState -> when (item.exercise.id) {
                            exerciseId -> item.copy(isSelected = !item.isSelected)
                            else -> item
                        }
                        else -> item
                    }
                }
            )
        }
    }

    private fun changeExpandState(muscleGroupId: MuscleGroupId) {
        modify {
            copy(
                items = items.map { item ->
                    when (item) {
                        is TrainingCreatorState.ExerciseState -> when (item.muscleGroupId) {
                            muscleGroupId -> item.copy(isInExpandedGroup = !item.isInExpandedGroup)
                            else -> item
                        }
                        is TrainingCreatorState.MuscleGroupState -> when (item.muscleGroupId) {
                            muscleGroupId -> item.copy(isExpanded = !item.isExpanded)
                            else -> item
                        }
                    }
                }
            )
        }
    }

}