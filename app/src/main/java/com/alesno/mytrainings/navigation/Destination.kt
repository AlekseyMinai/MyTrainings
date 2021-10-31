package com.alesno.mytrainings.navigation

import androidx.annotation.DrawableRes
import com.alesno.mytrainings.R
import com.alexey.minay.core_training.TrainingId
import com.alexey.minay.core_training.TrainingTypeId
import com.alexey.minay.core_utils.asString
import com.alexey.minay.core_training.TrainingProgramId

sealed class Destination(
    private var args: List<String> = emptyList(),
    val routePart: String
) {

    open val route: String get() = routePart + args.asString { "/$it" }

    class Training(
        trainingTypeId: TrainingTypeId? = null,
        trainingId: TrainingId? = null
    ) : Destination(
        routePart = "training",
        args = listOf(
            trainingTypeId?.value?.toString() ?: "{$KEY_TRAINING_INFO_ID}",
            trainingId?.value?.toString() ?: "{$KEY_TRAINING_ID}"
        )
    ) {
        companion object {
            const val KEY_TRAINING_INFO_ID = "training_info_id"
            const val KEY_TRAINING_ID = "training_id"
        }
    }

    class TrainingList(
        programId: TrainingProgramId? = null
    ) : Destination(
        routePart = "training_list",
        args = listOf(programId?.value?.toString() ?: "{$KEY_PROGRAM_ID}")
    ) {
        companion object {
            const val KEY_PROGRAM_ID = "program_id"
        }
    }

    class Home(val item: HomeItem) : Destination(routePart = "home") {
        override val route: String
            get() = routePart + "/" + item.routePart
    }

    enum class HomeItem(
        val routePart: String,
        @DrawableRes val iconRes: Int,
        val title: String
    ) {
        HISTORY("history", R.drawable.ic_history, "История"),
        PROGRAM("program", R.drawable.ic_home, "Программы")
    }

}