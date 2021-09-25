package com.alesno.mytrainings.di

import androidx.navigation.NavController
import com.alexey.minay.core_dagger_2.MultiViewModelFactory
import dagger.Component

@Component(
    modules = [StoreBindings::class, AppModule::class],
    dependencies = [NavController::class]
)
interface AppComponent {

    val viewModelFactory: MultiViewModelFactory

}