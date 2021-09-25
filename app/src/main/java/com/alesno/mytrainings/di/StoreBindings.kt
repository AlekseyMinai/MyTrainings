package com.alesno.mytrainings.di

import androidx.lifecycle.ViewModel
import com.alexey.minay.core_dagger_2.StoreKey
import com.alexey.minay.feature_training.presentation.TrainingStore
import com.alexey.minay.feature_training_list.presentation.TrainingListStore
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
interface StoreBindings {

    @Binds
    @[IntoMap StoreKey(TrainingStore::class)]
    fun bindTrainingStore(trainingStore: TrainingStore): ViewModel

//    @Binds
//    @[IntoMap StoreKey(TrainingListStore::class)]
//    fun bindTrainingListStore(trainingListStore: TrainingListStore): ViewModel

}