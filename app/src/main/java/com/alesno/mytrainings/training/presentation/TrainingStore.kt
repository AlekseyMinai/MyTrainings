package com.alesno.mytrainings.training.presentation

import com.alesno.mytrainings.domain.Training
import com.alesno.mytrainings.domain.TrainingExerciseId
import com.alesno.mytrainings.domain.TrainingSet
import com.alesno.mytrainings.fake.FakeData
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch

class TrainingStore {

    val trainingState: Flow<Training>
        get() = mTrainingState
    private val mTrainingState by lazy { MutableStateFlow(FakeData.createTraining()) }

    suspend fun proceed(intent: TrainingIntent) {
        when (intent) {
            is TrainingIntent.AddSet -> addSet(intent.id, intent.weight, intent.count)
        }
    }

    private suspend fun addSet(id: TrainingExerciseId, weight: Int, count: Int) {
        GlobalScope.launch {
            var trainingExercise = mTrainingState.value
                .exercises
                .firstOrNull { it.id.value == id.value }

            trainingExercise = trainingExercise?.copy(
                sets = trainingExercise.sets
                    .toMutableList()
                    .apply { add(TrainingSet(weight, count)) }
            ) ?: return@launch

            var exercises = mTrainingState.value
                .exercises
                .toMutableList()

            var updatedExerciseIndex: Int? = null
            exercises = exercises.filterIndexed { index, exercise ->
                if (exercise.id.value == id.value) {
                    updatedExerciseIndex = index
                    false
                } else true
            }.toMutableList()
            updatedExerciseIndex?.let { exercises.add(it, trainingExercise) }

            val state = mTrainingState.value
                .copy(exercises = exercises)

            mTrainingState.emit(state)
        }
    }

}