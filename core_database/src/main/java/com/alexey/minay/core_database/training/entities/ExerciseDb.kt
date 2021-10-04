package com.alexey.minay.core_database.training.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "Exercises")
data class ExerciseDb(
    val title: String,
) {
    @PrimaryKey(autoGenerate = true)
    var exerciseId: Long = 0
}