package com.alesno.mytrainings.di

import android.content.Context
import com.alexey.minay.core_database.AppDatabase
import dagger.BindsInstance
import dagger.Component

@AppScope
@Component(
    modules = [AppModule::class]
)
interface AppComponent {

    val appDatabase: AppDatabase

    @Component.Builder
    interface Builder {
        @BindsInstance
        fun context(context: Context): Builder
        fun build(): AppComponent
    }

}