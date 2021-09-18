package com.alexey.minay.feature_training_list.presentation

import com.alexey.minay.feature_training_list.domain.TrainingInfoId


sealed interface TrainingListIntent {
    class StartNewTraining(trainingInfoId: TrainingInfoId) : TrainingListIntent
}

sealed interface TrainingListEvent {

}