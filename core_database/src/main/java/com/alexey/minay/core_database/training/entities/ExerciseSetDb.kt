package com.alexey.minay.core_database.training.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "Sets")
data class ExerciseSetDb(
    val weight: Int,
    val count: Int,
    val exerciseDb: Long
) {
    @PrimaryKey(autoGenerate = true)
    var setId: Long = 0
}