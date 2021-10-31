package com.alexey.minay.core_database.training.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "TrainingPrograms")
data class TrainingProgramsDb(
    val title: String
) {
    @PrimaryKey(autoGenerate = true)
    var programId: Long = 0
}