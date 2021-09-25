package com.alexey.minay.feature_training.presentation

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.alexey.minay.core_ui.Store
import com.alexey.minay.feature_training.domain.Training
import com.alexey.minay.feature_training.domain.TrainingId
import java.util.*
import javax.inject.Inject

class TrainingStore @Inject constructor(
    initialState: TrainingState
) : Store<TrainingState, TrainingIntent, TrainingEvent>(initialState) {

    init {
        Log.d("TrainingStore", "TrainingStore $this")
    }

    override suspend fun execute(intent: TrainingIntent) {
        when (intent) {
            is TrainingIntent.AddSet -> {
                modify {
                    copy(training = Training(TrainingId(2), "sdf", Date(), emptyList()))
                }
            }
        }
    }

    companion object {
        fun provideFactory(
            trainingState: TrainingState = TrainingState.default()
        ): ViewModelProvider.Factory =
            object : ViewModelProvider.Factory {
                override fun <T : ViewModel?> create(modelClass: Class<T>): T {
                    return TrainingStore(
                        initialState = trainingState
                    ) as T
                }

            }
    }

}