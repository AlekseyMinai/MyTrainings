package com.alesno.mytrainings.presentation.training

import com.alesno.mytrainings.domain.training.TrainingExerciseId

sealed interface TrainingIntent {
    class AddSet(val id: TrainingExerciseId, val weight: Int, val count: Int) : TrainingIntent
}

sealed interface TrainingEvent