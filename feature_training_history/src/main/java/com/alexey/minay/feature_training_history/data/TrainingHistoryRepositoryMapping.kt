package com.alexey.minay.feature_training_history.data

import com.alexey.minay.core_database.training.TrainingWithType
import com.alexey.minay.core_training.TrainingId
import com.alexey.minay.feature_training_history.domain.Training
import java.util.*

fun TrainingWithType.toDomain() = Training(
    id = TrainingId(training.trainingId),
    date = Date(training.date),
    title = trainingType.title
)