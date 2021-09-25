package com.alesno.mytrainings.navigation

import com.alexey.minay.core_navigation.asString
import com.alexey.minay.core_training.TrainingInfoId

sealed class Destination(
    private val routePart: String,
    private var args: List<String> = emptyList()
) {

    val route: String get() = routePart + args.asString { "/$it" }

    object TrainingList : Destination(routePart = "training_list")

    class Training(trainingInfoId: TrainingInfoId? = null) : Destination(
        routePart = "training",
        args = listOf(trainingInfoId?.value ?: "{$KEY_TRAINING_INFO_ID}")
    ) {
        companion object {
            const val KEY_TRAINING_INFO_ID = "training_info_id"
        }
    }

}