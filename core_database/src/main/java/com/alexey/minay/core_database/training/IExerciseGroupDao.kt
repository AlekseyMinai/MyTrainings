package com.alexey.minay.core_database.training

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Transaction
import com.alexey.minay.core_database.training.entities.TrainingTypeDb
import com.alexey.minay.core_database.training.entities.TrainingTypeExercisesCrossRefDb

@Dao
abstract class IExerciseGroupDao {

    @Query("SELECT * FROM MuscleGroup")
    abstract fun getExerciseGroup(): List<MuscleGroupWithExercises>

    @Transaction
    open fun insertTraining(trainingDb: TrainingTypeDb, exerciseIds: List<Long>) {
        val trainingTypeId = insertTrainingType(trainingDb)
        val trainingTypeExercisesCrossRefs = exerciseIds.map { exerciseId ->
            TrainingTypeExercisesCrossRefDb(
                trainingTypeId = trainingTypeId,
                exerciseId = exerciseId
            )
        }

        insertExercisesCrossRef(trainingTypeExercisesCrossRefs)
    }

    @Insert
    abstract fun insertTrainingType(trainingDb: TrainingTypeDb): Long

    @Insert
    abstract fun insertExercisesCrossRef(exerciseCorRefs: List<TrainingTypeExercisesCrossRefDb>)
}