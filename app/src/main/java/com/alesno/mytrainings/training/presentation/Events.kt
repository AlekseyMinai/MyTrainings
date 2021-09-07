package com.alesno.mytrainings.training.presentation

import com.alesno.mytrainings.domain.TrainingExerciseId

sealed class TrainingIntent {
    class AddSet(val id: TrainingExerciseId, val weight: Int, val count: Int) : TrainingIntent()
}