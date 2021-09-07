package com.alesno.mytrainings.fake

import com.alesno.mytrainings.domain.*
import java.util.*

object FakeData {

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
                )
            )
        )
    }

}