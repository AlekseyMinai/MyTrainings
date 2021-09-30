package com.alesno.mytrainings.di

import android.content.Context
import androidx.room.Room
import com.alexey.minay.core_database.AppDatabase
import com.alexey.minay.core_utils.CoroutineDispatchersProvider
import dagger.Module
import dagger.Provides

@Module
class AppModule {

    @Provides
    @AppScope
    fun provideDatabase(context: Context): AppDatabase {
        return Room.databaseBuilder(context, AppDatabase::class.java, "MyTrainingsDatabase")
            .createFromAsset("init_data.db")
            .build()
    }

    @Provides
    @AppScope
    fun provideCoroutineDispatcherProvider() = CoroutineDispatchersProvider()

}