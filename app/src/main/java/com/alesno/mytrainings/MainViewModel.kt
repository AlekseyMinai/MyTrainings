package com.alesno.mytrainings

import androidx.lifecycle.ViewModel
import com.alesno.mytrainings.fake.FakeData
import com.alesno.mytrainings.presentation.training.TrainingState
import com.alesno.mytrainings.presentation.training.TrainingStore
import com.alesno.mytrainings.presentation.trainingList.TrainingListState
import com.alesno.mytrainings.presentation.trainingList.TrainingListStore

class MainViewModel : ViewModel() {

    val trainingStore = TrainingStore(TrainingState(FakeData.createTraining()))
    val trainingListStore = TrainingListStore(TrainingListState(FakeData.getTrainings()))

}