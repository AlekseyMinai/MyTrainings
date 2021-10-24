package com.alexey.minay.feature_training.presentation

import com.alexey.minay.core_training.TrainingId
import com.alexey.minay.core_training.TrainingTypeId
import com.alexey.minay.feature_training.domain.Training

data class TrainingState(
    val training: Training,
    val editSetDialogState: EditSetDialogState?,
    val type: Type
) {

    enum class Type {
        DEFAULT,
        EDIT_SET
    }

    companion object {
        fun default(trainingTypeId: TrainingTypeId?, trainingId: TrainingId?) =
            TrainingState(
                training = Training.default(trainingTypeId, trainingId),
                editSetDialogState = null,
                type = Type.DEFAULT
            )
    }
}