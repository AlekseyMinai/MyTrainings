package com.alesno.mytrainings

import androidx.lifecycle.ViewModel
import com.alesno.mytrainings.training.presentation.TrainingStore

class MainViewModel : ViewModel() {

    val trainingStore = TrainingStore()

}