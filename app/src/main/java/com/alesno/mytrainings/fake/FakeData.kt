package com.alesno.mytrainings.fake

import com.alesno.mytrainings.domain.training.*
import com.alesno.mytrainings.domain.trainingList.TrainingInfo
import com.alesno.mytrainings.domain.trainingList.TrainingInfoId
import java.util.*

object FakeData {

    fun getTrainings() = mutableListOf<TrainingInfo>()
        .apply {
            add(TrainingInfo(TrainingInfoId("1"), "Грудь бицепс"))
            add(TrainingInfo(TrainingInfoId("2"), "Спина трицепс"))
            add(TrainingInfo(TrainingInfoId("3"), "Ноги"))
        }

    fun createTraining(): Training {
        return Training(
            id = TrainingId(1L),
            name = "Грудь и бицепс",
            date = Date(),
            exercises = mutableListOf(
                TrainingExercise(
                    id = TrainingExerciseId(1L),
                    info = ExerciseInfo(
                        name = "Жим штанги лежа",
                        muscleGroup = "Грудь"
                    ),
                    sets = mutableListOf(
                        TrainingSet(5, 10),
                        TrainingSet(50, 10),
                        TrainingSet(5, 10),
                        TrainingSet(5000, 10),
                        TrainingSet(50, 10),
                        TrainingSet(50, 10)
                    )
                ),
                TrainingExercise(
                    id = TrainingExerciseId(2L),
                    info = ExerciseInfo(
                        name = "Раводка гантелей лежа",
                        muscleGroup = "Грудь"
                    ),
                    sets = mutableListOf(
                        TrainingSet(20, 10),
                        TrainingSet(20, 10),
                        TrainingSet(20, 10),
                        TrainingSet(20, 10)
                    )
                ),
                TrainingExercise(
                    id = TrainingExerciseId(3L),
                    info = ExerciseInfo(
                        name = "Empty",
                        muscleGroup = "Грудь"
                    ),
                    sets = emptyList()
                )
            )
        )
    }

}