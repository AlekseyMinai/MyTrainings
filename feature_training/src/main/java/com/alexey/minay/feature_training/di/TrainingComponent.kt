package com.alexey.minay.feature_training.di

import android.util.Log
import com.alexey.minay.feature_training.presentation.TrainingStoreProvider
import dagger.Component

@Component(modules = [StoreBindings::class, TrainingModule::class])
interface TrainingComponent {

    val trainingStoreProvider: TrainingStoreProvider

    companion object {

        var trainingComponent: TrainingComponent? = null

        fun initAndGet(): TrainingComponent {
            Log.d("TrainingComponent", "TrainingComponent $trainingComponent")
            return trainingComponent ?: DaggerTrainingComponent.create().apply {
                trainingComponent = this
            }
        }

        fun release() {
            trainingComponent = null
        }

    }

}