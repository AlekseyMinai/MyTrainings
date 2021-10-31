package com.alexey.minay.core_database.training

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.alexey.minay.core_database.training.entities.TrainingTypeDb

@Dao
interface ITrainingListDao {
    @Insert
    fun insert(trainingInoList: List<TrainingTypeDb>)

    @Query(
        """SELECT * FROM TrainingTypes INNER JOIN TrainingProgramTrainingTypeCrossRef ON 
        TrainingTypes.trainingTypeId = TrainingProgramTrainingTypeCrossRef.trainingTypeId WHERE
        TrainingProgramTrainingTypeCrossRef.programId = :programId
        """
    )
    fun getAllFor(programId: Long): List<TrainingTypeDb>
}