package com.alexey.minay.core_database.training.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "MuscleGroup")
data class MuscleGroupDb(
    val title: String
){
    @PrimaryKey(autoGenerate = true)
    var muscleGroupId: Long = 0
}