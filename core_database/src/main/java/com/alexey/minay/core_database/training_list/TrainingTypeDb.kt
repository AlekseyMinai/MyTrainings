package com.alexey.minay.core_database.training_list

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "TrainingTypeDb")
data class TrainingTypeDb(
    @PrimaryKey(autoGenerate = true)
    val id: Long = 0,
    val title: String
)