package com.alesno.mytrainings.di

import android.content.Context
import com.alexey.minay.core_database.AppDatabase
import dagger.Component

@Component(
    modules = [AppModule::class],
    dependencies = [Context::class]
)
@AppScope
interface AppComponent {

    val appDatabase: AppDatabase

}