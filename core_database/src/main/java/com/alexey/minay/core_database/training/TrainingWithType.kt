package com.alexey.minay.core_database.training

import androidx.room.Embedded
import androidx.room.Relation
import com.alexey.minay.core_database.training.entities.TrainingDb
import com.alexey.minay.core_database.training.entities.TrainingTypeDb

data class TrainingWithType(
    @Embedded
    val training: TrainingDb,
    @Relation(parentColumn = "trainingTypeId", entityColumn = "trainingTypeId")
    val trainingType: TrainingTypeDb,
)