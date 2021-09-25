package com.alexey.minay.feature_training_list.fake

import com.alexey.minay.feature_training_list.domain.TrainingInfo
import com.alexey.minay.feature_training_list.domain.TrainingInfoId
import java.util.*

object FakeData {

    fun getTrainings() = mutableListOf<TrainingInfo>()
        .apply {
            add(TrainingInfo(TrainingInfoId("1"), "Грудь бицепс"))
            add(TrainingInfo(TrainingInfoId("2"), "Спина трицепс"))
            add(TrainingInfo(TrainingInfoId("3"), "Ноги"))
        }

//    fun createTraining(): Training {
//        return Training(
//            id = TrainingId(1L),
//            name = "Грудь и бицепс",
//            date = Date(),
//            exercises = mutableListOf(
//                TrainingExercise(
//                    id = TrainingExerciseId(1L),
//                    info = ExerciseInfo(
//                        name = "Жим штанги лежа",
//                        muscleGroup = "Грудь"
//                    ),
//                    sets = mutableListOf(
//                        TrainingSet(5, 10),
//                        TrainingSet(50, 10),
//                        TrainingSet(5, 10),
//                        TrainingSet(5000, 10),
//                        TrainingSet(50, 10),
//                        TrainingSet(50, 10)
//                    )
//                ),
//                TrainingExercise(
//                    id = TrainingExerciseId(2L),
//                    info = ExerciseInfo(
//                        name = "Раводка гантелей лежа",
//                        muscleGroup = "Грудь"
//                    ),
//                    sets = mutableListOf(
//                        TrainingSet(20, 10),
//                        TrainingSet(20, 10),
//                        TrainingSet(20, 10),
//                        TrainingSet(20, 10)
//                    )
//                ),
//                TrainingExercise(
//                    id = TrainingExerciseId(3L),
//                    info = ExerciseInfo(
//                        name = "Empty",
//                        muscleGroup = "Грудь"
//                    ),
//                    sets = emptyList()
//                )
//            )
//        )
//    }

}