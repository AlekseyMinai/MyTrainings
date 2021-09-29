package com.alesno.mytrainings.di

import android.content.Context
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import com.alexey.minay.core_database.AppDatabase
import dagger.Module
import dagger.Provides

@Module
class AppModule {

    @Provides
    @AppScope
    fun provideDatabase(context: Context): AppDatabase {
        return Room.databaseBuilder(context, AppDatabase::class.java, "MyTrainingsDatabase")
            .addCallback(object : RoomDatabase.Callback() {
                override fun onCreate(db: SupportSQLiteDatabase) {
                    super.onCreate(db)
                    db.run {
                        beginTransaction()
                        try {
                            execSQL("INSERT INTO TrainingListDb VALUES(0, \"Грудь и бицепс\")")
                            execSQL("INSERT INTO TrainingListDb VALUES(1, \"Спина и трицепс\")")
                            execSQL("INSERT INTO TrainingListDb VALUES(2, \"Ноги\")")
                        } finally {
                            endTransaction()
                        }
                    }

                }
            }).build()
    }

}