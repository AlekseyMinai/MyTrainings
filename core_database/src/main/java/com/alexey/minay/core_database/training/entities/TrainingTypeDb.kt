package com.alexey.minay.core_database.training.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "TrainingTypes")
data class TrainingTypeDb(
    val title: String
) {
    @PrimaryKey(autoGenerate = true)
    var trainingTypeId: Long = 0
}