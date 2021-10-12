package com.alexey.minay.core_database.training.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "Trainings")
data class TrainingDb(
    val date: Long,
    val trainingTypeId: Long
) {
    @PrimaryKey(autoGenerate = true)
    var trainingId: Long = 0
}