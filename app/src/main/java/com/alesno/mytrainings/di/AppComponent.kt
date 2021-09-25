package com.alesno.mytrainings.di

import androidx.navigation.NavController
import dagger.Component

@Component(
    modules = [AppModule::class],
    dependencies = [NavController::class]
)
interface AppComponent {


}