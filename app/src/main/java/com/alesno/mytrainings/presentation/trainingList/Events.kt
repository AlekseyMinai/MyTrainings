package com.alesno.mytrainings.presentation.trainingList

import com.alesno.mytrainings.domain.trainingList.TrainingInfoId

sealed interface TrainingListIntent {
    class StartNewTraining(trainingInfoId: TrainingInfoId) : TrainingListIntent
}

sealed interface TrainingListEvent {

}