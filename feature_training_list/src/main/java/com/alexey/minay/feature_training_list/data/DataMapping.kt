package com.alexey.minay.feature_training_list.data

import com.alexey.minay.core_database.training_list.TrainingTypeDb
import com.alexey.minay.core_training.TrainingTypeId
import com.alexey.minay.feature_training_list.domain.TrainingType

fun TrainingType.toDb() = TrainingTypeDb(
    title = title
)

fun TrainingTypeDb.toDomain() = TrainingType(
    id = TrainingTypeId(id),
    title = title
)